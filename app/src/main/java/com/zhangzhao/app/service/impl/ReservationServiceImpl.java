package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.ReservationService;
import com.zhangzhao.app.vo.*;
import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.dto.ReservationDto;
import com.zhangzhao.common.dto.ReservationsDto;
import com.zhangzhao.common.entity.*;
import com.zhangzhao.common.util.UtilDate;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 预约
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReservationServiceImpl extends BaseService implements ReservationService {

    /**
     * 预约申请下单
     *
     * @param reservationDto
     * @return
     */
    @Override
    public StatusOneVo save(ReservationDto reservationDto, BindingResult bindingResult) {
        StatusOneVo<MembersVo> vo = new StatusOneVo();
        MembersVo membersVo1 = new MembersVo();
        if (bindingResult.hasErrors()) {
            vo.fail(ErrorCode.PARAMETER_ERROR, bindingResult.getFieldError().getDefaultMessage());
            return vo;
        } else {

            try {
                if (reservationDto.getYesnoShopnote() == 1) {
                    Optional<Store> store = storeRepository.findById(reservationDto.getStoreId());
                    String orderNum = UtilDate.getOrderNum();
                    Reservation reservation = reservationMapper.dtoToBean(reservationDto, getUser().getId(), Reservation.Status.Waiting_list.getStatus());
                    reservation.setReservationNumber(orderNum);
                    reservation.setStore(store.get());
                    Reservation reservation1 = reservationRepository.save(reservation);
                    membersVo1.setOrderId(reservation1.getId());
                    vo.success(membersVo1);
                } else if (reservationDto.getYesnoShopnote() == 2) {
                    String orderNum = UtilDate.getOrderNum();
                    Reservation reservation = reservationMapper.dtoToBean(reservationDto, getUser().getId(), Reservation.Status.Waiting_list.getStatus());
                    reservation.setReservationNumber(orderNum);
                    Reservation reservation1 = reservationRepository.save(reservation);
                    membersVo1.setOrderId(reservation1.getId());
                    vo.success(membersVo1);
                }
                Optional<User> user = userRepository.findById(getUser().getId());
                membersVo1.setMember(user.get().getMember());
                membersVo1.setGratisTime(user.get().getGratisTime());
                vo.success(membersVo1);
            } catch (Exception e) {
                vo.fail(ErrorCode.SYSTEM_ERROR, "下单失败");
            }
            return vo;
        }
    }

    /**
     * 修改预约地址
     *
     * @param reservationsDto
     * @return
     */
    @Override
    public StatusVoidVo updateAddress(ReservationsDto reservationsDto) {
        StatusVoidVo vo = new StatusVoidVo();
        try {

            reservationRepository.updateAddress(reservationsDto.getId(), reservationsDto.getProvince(),
                    reservationsDto.getDistrict(), reservationsDto.getCity(),
                    reservationsDto.getDetailedAddress());
        } catch (Exception e) {
            e.printStackTrace();
            vo.fail(ErrorCode.SYSTEM_ERROR, "修改地址失败");
        }
        vo.success();
        return vo;
    }

    /**
     * 预约订单状态
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public StatusVoidVo updateStatus(Long id, int status) {
        StatusVoidVo vo = new StatusVoidVo();
        reservationRepository.updateStatus(id, status);
        vo.success();
        return vo;
    }

    /**
     * 用户师傅确认
     *
     * @param id
     * @param statusOk
     * @return
     */
    @Override
    public StatusVoidVo updatestatusOk(Long id, int statusOk) {
        StatusVoidVo vo = new StatusVoidVo();
        Reservation reservation = new Reservation();
        reservationRepository.updatestatusOk(id, statusOk);
        if (statusOk == 1) {
            vo.fail(ErrorCode.PARAMETER_ERROR, "请先用户确认");
        } else if (statusOk == 2) {
            vo.fail(ErrorCode.PARAMETER_ERROR, "等待师傅确认");
        } else if (statusOk == 3) {
            reservation.setStatus(statusOk);
            vo.success();
        }
        return vo;
    }

    /**
     * 查询订单ID
     *
     * @param id
     * @return
     */
    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("预约订单为空" + id));
    }

    /**
     * 查询订单编号
     *
     * @param reservationNumber
     * @return
     */
    @Override
    public Reservation findByreservationNumber(String reservationNumber) {
        return reservationRepository.findByreservationNumber(reservationNumber);
    }

    /**
     * 预约订单列表
     *
     * @param status
     * @return
     */
    @Override
    public StatusVo<ReservationVo> findAll(Integer page, Integer pageSize, Long id, int type, int status) {
        StatusVo<ReservationVo> vo = new StatusVo<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "appointmentTime");
        Page<Reservation> reservationList = reservationRepository.findByUserIdAndYesnoShopnoteAndStatus(getUser().getId(), type, status, pageable);
        List<ReservationVo> collect = reservationList.getContent().parallelStream().map(reservationMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect);
        return vo;
    }

    /**
     * 到店订单列表
     *
     * @param id
     * @return
     */
    @Override
    public StatusVo<ReservationVo> findAllYesShopnote(Integer page, Integer pageSize, Long id) {
        StatusVo<ReservationVo> vo = new StatusVo<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "appointmentTime");
        Page<Reservation> reservationList = reservationRepository.findByUserIdAndType(getUser().getId(), 1, pageable);
        List<ReservationVo> collect = reservationList.getContent().parallelStream().map(reservationMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect);
        return vo;
    }

    @Override
    public StatusVo<ReservationVo> findAll2(Integer page, Integer pageSize, Long id) {
        StatusVo<ReservationVo> vo = new StatusVo<>();
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "appointmentTime");
        Page<Reservation> reservationList = reservationRepository.findByUserIdAndStatus(id, 1, pageable);
        List<ReservationVo> collect = reservationList.getContent().parallelStream().map(reservationMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect);
        return vo;
    }


    /**
     * 订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public StatusOneVo<ReservationOrderDetailsVo> findOrderId(Long orderId) {
        StatusOneVo<ReservationOrderDetailsVo> vo = new StatusOneVo<>();
        Reservation one = reservationRepository.getOne(orderId);
        ReservationOrderDetailsVo reservationOrderDetailsVo = reservationOrderDetailsMapper.beanToVo(one);
        vo.success(reservationOrderDetailsVo);
        return vo;
    }

    /**
     * 删除已完成订单
     *
     * @param id
     * @return
     */
    @Override
    public StatusVoidVo deleteOrder(Long id) {
        StatusVoidVo vo = new StatusVoidVo();
        try {
            reservationRepository.deleteById(id);

        } catch (Exception e) {
            vo.fail(ErrorCode.SYSTEM_ERROR, "删除错误");
        }
        vo.success();
        return vo;
    }

    /**
     * 预约类型
     *
     * @return
     */
    @Override
    public StatusVo<ReservationTypeVo> reservationType() {
        StatusVo<ReservationTypeVo> vo = new StatusVo<>();
        List<ReservationType> byYesnoShopnote = reservationtypeRepository.findAll();
        List<ReservationTypeVo> collect = byYesnoShopnote.stream().map(reservationTypeMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect);
        return vo;
    }

    /**
     * 金额 上下班
     *
     * @return
     */
    @Override
    public StatusOneVo<MoneyAndMasterVo> moneyAndMaster() {
        StatusOneVo<MoneyAndMasterVo> vo = new StatusOneVo<>();
        Optional<User> user = userRepository.findById(getUser().getId());
        Optional<MasterTime> masterTimeOptional = masterTimeRepository.findById(1L);
        if (!masterTimeOptional.isPresent() && !user.isPresent()) {
            vo.fail(ErrorCode.SYSTEM_ERROR, "系统错误");
        } else {
            MoneyAndMasterVo moneyAndMasterVo = reservationMapper.moneyToVo(user.get(), masterTimeOptional.get());
            vo.success(moneyAndMasterVo);
        }
        return vo;
    }
}
