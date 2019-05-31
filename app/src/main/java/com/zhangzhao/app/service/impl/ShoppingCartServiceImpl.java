package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.ShoppingCartService;
import com.zhangzhao.app.vo.ShoppingCartVo;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.entity.ShoppingCart;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 购物车
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShoppingCartServiceImpl extends BaseService implements ShoppingCartService {

    @Override
    public StatusVo<ShoppingCartVo> findAlls() {
        List<ShoppingCart> page1 = shoppingCartRepository.findByUserId(getUser().getId());
        List<ShoppingCartVo> collect = page1.parallelStream().map(shoppingCartMapper::beanToVo).collect(Collectors.toList());
        StatusVo<ShoppingCartVo> vo=new StatusVo<>();
        vo.success(collect);
        return vo;
    }

    @Override
    public StatusVoidVo delBean(List ids) {
        StatusVoidVo vo=new StatusVoidVo();
        if (ids!=null && ids.size()>0){
            shoppingCartRepository.deleteByIdIn(ids);
            vo.success();
        }else {
            vo.setMsg("id不能为空");
        }
        return vo;
    }

    @Override
    public StatusVoidVo upAmount(Long id, Integer amount) {
        StatusVoidVo vo=new StatusVoidVo();
        if (id>0 && amount>0){
            Optional<ShoppingCart> optional = shoppingCartRepository.findById(id);
            if (optional.isPresent()){
                Optional<GoodsCommodity> goodsCommodity = goodsCommodityRepository.findById(optional.get().getGoodId());
                if (goodsCommodity.isPresent() && optional.get().getAmount()+amount<=goodsCommodity.get().getInventory()){
                    shoppingCartRepository.upAmount(id,amount);
                    vo.success();
                }else {
                    vo.setMsg("商品数量不足修改");
                }
            }
        }else {
            vo.setMsg("id或数量不能为空");
        }
        return vo;
    }

    @Override
    public StatusVoidVo addBeans(Long id, Integer type, String installationType, int amount) {
        StatusVoidVo vo=new StatusVoidVo();
        Optional<GoodsCommodity> optional = goodsCommodityRepository.findById(id);
        if (optional.isPresent()){
            ShoppingCart cart;
            Optional<ShoppingCart> goodId = shoppingCartRepository.findByGoodIdAndInstallationTypeAndTypeAndUserId(id,installationType,type,getUser
                    ().getId());
            if (goodId.isPresent()){
                cart = goodId.get();
                cart.setAmount(cart.getAmount()+amount);
            }else {
                GoodsCommodity goodsCommodity = optional.get();
                cart = shoppingCartMapper.goodToCart(goodsCommodity);
                cart.setId(0L);
                cart.setAmount(amount);
                cart.setInstallationType(installationType);
                if (type!=null){
                    cart.setType(type);
                }
                cart.setGoodId(id);
                cart.setUserId(getUser().getId());
                cart.setCreateTime(new Date());
            }
            shoppingCartRepository.saveAndFlush(cart);
            vo.success();
        }else {
            vo.setMsg("商品不存在");
        }
        return vo;
    }
}
