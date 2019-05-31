package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.vo.OrderDetailsVo;
import com.zhangzhao.app.vo.OrderSupplyVo;
import com.zhangzhao.app.vo.ReturnPolicyVo;
import com.zhangzhao.common.entity.*;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.ReturnPolicyService;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  退换货
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnServiceImpl extends BaseService implements ReturnPolicyService {

    @Override
    public StatusOneVo refund(Long id) {
        StatusOneVo vo = new StatusOneVo();
        Optional<OrderSupply> optional = orderSupplyRepository.findById(id);
        if (optional.isPresent()){
            OrderSupply orderSupply = optional.get();
            OrderSupplyVo orderSupplyVo = orderSupplyMapper.beanToVo(orderSupply);
            vo.success(orderSupplyVo);
        }
        return vo;
    }

    @Override
    public StatusVoidVo refundPer(Long id, String why, String instructions) {
        StatusVoidVo vo=new StatusVoidVo();
        Optional<OrderSupply> optional = orderSupplyRepository.findById(id);
        if (optional.isPresent()){
            OrderSupply orderSupply = optional.get();
            ReturnPolicy returnPolicy = new ReturnPolicy();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,2);
            returnPolicy.setType(ReturnPolicy.Type.RETURN.getType());
            returnPolicy.setWhy(why);
            returnPolicy.setInstructions(instructions);
            returnPolicy.setMoney(orderSupply.getPaymentPrcie());
            returnPolicy.setRefundDate(calendar.getTime());
            returnPolicy.setOrderSupply(orderSupply);
            returnPolicy.setUserId(getUser().getId());
            returnPolicy.setStatus(ReturnPolicy.Status.APPLY_REFUND.getStatus());
            returnPolicyRepository.saveAndFlush(returnPolicy);
            orderSupply.setStatus(OrderSupply.Status.APPLY.getStatus());
            orderSupplyRepository.save(orderSupply);
            vo.success();
        }
        return vo;
    }

    @Override
    public StatusVo afterSale(List<Long> ids) {
        StatusVo vo = new StatusVo();
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllById(ids);
       if (orderDetailsList!=null && orderDetailsList.size()>0){
           double prci = 0.00;
            List<OrderDetailsVo> list = orderDetailsList.parallelStream().map(orderDetailsMapper::beanToVo).collect(Collectors.toList());
           for(OrderDetails orderDetails : orderDetailsList){
               List<OrderSupply> orderAll = orderSupplyRepository.findByUserId(orderDetails.getOrderId());
               List<OrderDetails> orderList = orderDetailsRepository.findByOrderId(orderDetails.getOrderId());
               if(orderList.size() == orderDetailsList.size()){
                   for(OrderDetailsVo orderDetailsVo : list){
                       for(OrderSupply order : orderAll){
                           BigDecimal orderPrcie = new BigDecimal(order.getOrderPrcie());//订单总价格
                           BigDecimal freight = new BigDecimal(order.getFreightPrcie()); //物流费
                           orderDetailsVo.setOrderPrcie(orderPrcie.toString());
                           orderDetailsVo.setFreightPrcie(freight.toString());
                          vo.success(list);
                       }
                   }
               }else{
                   for(int i=0; i<orderDetailsList.size(); i++){
                       prci += orderDetails.getPaymentPrcie();
                   }
                   for(OrderDetailsVo orderDetailsVo : list){
                       orderDetailsVo.setPrcieAll(String.valueOf(prci));
                       vo.success(list);
                   }
               }
           }
            vo.success(list);
        }else {
            vo.setMsg("不存在该订单详情");
        }
        return vo;
    }

    @Override
    public StatusVoidVo salePer(List<Long> ids,Long id, String why, String instructions, String img, int type) {
        StatusVoidVo vo=new StatusVoidVo();
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAllById(ids);
        Optional<OrderSupply> optional = orderSupplyRepository.findById(id);
        if (optional.isPresent()){
            OrderSupply orderSupply = optional.get();
            ReturnPolicy returnPolicy = new ReturnPolicy();
            returnPolicy.setType(type);
            orderSupply.setStatus(OrderSupply.Status.COMPLETE.getStatus());
            if (type==3){
                double val = orderDetailsList.stream().mapToDouble(OrderDetails::getPaymentPrcie).sum();
                returnPolicy.setWhy(why);
                returnPolicy.setImg(img);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE,7);
                returnPolicy.setRefundDate(calendar.getTime());
                returnPolicy.setMoney(val);
                orderSupply.setStatus(OrderSupply.Status.APPLY_REFUND.getStatus());
            }
            returnPolicy.setProductId(StringUtils.join(orderDetailsList.parallelStream().map(o->o.getGoodsCommodityId()).collect(Collectors.toList
                    ()), ","));
            returnPolicy.setInstructions(StringUtils.isNotBlank(instructions)?instructions:"");
            returnPolicy.setUserId(getUser().getId());
            returnPolicy.setOrderSupply(orderSupply);
            returnPolicy.setStatus(ReturnPolicy.Status.APPLY_REFUND.getStatus());
            returnPolicyRepository.saveAndFlush(returnPolicy);
            orderSupplyRepository.save(orderSupply);
            vo.success();
        }else {
            vo.setMsg("不存在该订单详情");
        }
        return vo;
    }

    @Override
    public StatusVo saleList(Integer page, Integer pageSize) {
        PageRequest pageable = PageRequest.of(page-1, pageSize, Sort.Direction.DESC, "createTime");
        Page<ReturnPolicy> all = returnPolicyRepository.findByUserId(getUser().getId(), pageable);
        List<ReturnPolicyVo> collect = all.getContent().parallelStream().map(o->{
            if (o.getType()!=1){
                List<OrderDetails> orderDetails = o.getOrderSupply().getOrderDetails();
                for (int i=0;i<orderDetails.size();i++){
                    if (!o.getProductId().contains(orderDetails.get(i).getGoodsCommodityId().toString())){
                        orderDetails.remove(i);
                        i--;
                    }
                }
            }
            return returnPolicyMapper.beanToVo(o);
        }).collect(Collectors.toList());
        StatusVo vo = new StatusVo();
        vo.success(collect);
        return vo;
    }

    @Override
    public StatusOneVo saleDetail(Long id) {
        StatusOneVo vo=new StatusOneVo();
        Optional<ReturnPolicy> optional = returnPolicyRepository.findById(id);
        if (optional.isPresent()){
            ReturnPolicyVo returnPolicyVo = returnPolicyMapper.beanToVo(optional.get());
            if (optional.get().getOrderSupply().getStore()!=null){
                returnPolicyVo.getOrderSupply().setStore(storeMapper.beanToVo(optional.get().getOrderSupply().getStore()));
            }
            if (optional.get().getOrderSupply().getInvoiceId()>0){
                Optional<Invoice> invoiceOptional = invoiceRepository.findById(optional.get().getOrderSupply().getInvoiceId());
                if (invoiceOptional.isPresent()){
                    returnPolicyVo.getOrderSupply().setInvoice(invoiceMapper.beanToVo(invoiceOptional.get()));
                }
            }
            if (optional.get().getOrderSupply().getAddressId()>0){
                Optional<Address> addressOptional = addressRepository.findById(optional.get().getOrderSupply().getAddressId());
                if (addressOptional.isPresent()){
                    returnPolicyVo.getOrderSupply().setAddress(addressMapper.beanToVo(addressOptional.get()));
                }
            }
            vo.success(returnPolicyVo);
        }
        return vo;
    }

    @Override
    public StatusVoidVo reissuePer(Long id, int status) {
        StatusVoidVo vo=new StatusVoidVo();
        if (status==ReturnPolicy.Status.ALREADY_COMPLETE.getStatus()){
            Optional<ReturnPolicy> optional = returnPolicyRepository.findById(id);
            if (optional.isPresent()){
                ReturnPolicy returnPolicy = optional.get();
                OrderSupply orderSupply = returnPolicy.getOrderSupply();
                orderSupply.setStatus(OrderSupply.Status.REISSUE.getStatus());
                returnPolicy.setStatus(status);
                returnPolicyRepository.save(returnPolicy);
                orderSupplyRepository.save(orderSupply);
            }
//            returnPolicyRepository.upStatus(id,status);
            vo.success();
        }else {
            vo.setMsg("参数不合法");
        }
        return vo;
    }

    @Override
    public StatusVoidVo reissueNumber(Long id, String number,String reissueNumber) {
        StatusVoidVo vo=new StatusVoidVo();
        if (StringUtils.isNotBlank(number)){
            returnPolicyRepository.reissueNumber(id,number,reissueNumber);
            vo.success();
        }else {
            vo.setMsg("参数不合法");
        }
        return vo;
    }
}
