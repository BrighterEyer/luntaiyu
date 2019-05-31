package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.*;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.ReservationDto;
import com.zhangzhao.common.dto.ReservationsDto;
import com.zhangzhao.common.entity.Reservation;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.validation.BindingResult;

public interface ReservationService extends CommonService {
    /**
     * 预约申请
     *
     * @param reservationDto
     * @return
     */
    StatusOneVo save(ReservationDto reservationDto, BindingResult bindingResult);

    /**
     * 修改预约地址
     *
     * @param reservationsDto
     * @return
     */
    StatusVoidVo updateAddress(ReservationsDto reservationsDto);

    /**
     * 预约订单状态
     *
     * @param id
     * @param status
     * @return
     */
    StatusVoidVo updateStatus(Long id, int status);

    /**
     * 订单确认
     *
     * @param id
     * @param statusOk
     * @return
     */
    StatusVoidVo updatestatusOk(Long id, int statusOk);

    /**
     * 查询订单ID
     *
     * @param id
     * @return
     */
    Reservation findById(Long id);

    /**
     * 查询订单编号
     *
     * @param reservationNumber
     * @return
     */
    Reservation findByreservationNumber(String reservationNumber);

    /**
     * 预约订单列表
     *
     * @param id
     * @param type
     * @param status
     * @return
     */
    StatusVo<ReservationVo> findAll(Integer page, Integer pageSize, Long id, int type, int status);

    /**
     * 到店订单列表
     *
     * @param id
     * @return
     */
    StatusVo<ReservationVo> findAllYesShopnote(Integer page, Integer pageSize, Long id);

    /**
     * 待接单列表
     *
     * @param id
     * @return
     */
    StatusVo<ReservationVo> findAll2(Integer page, Integer pageSize, Long id);


    /**
     * 订单详情
     *
     * @param orderId
     * @return
     */

    StatusOneVo<ReservationOrderDetailsVo> findOrderId(Long orderId);

    /**
     * 删除已完成订单
     *
     * @param id
     * @return
     */
    StatusVoidVo deleteOrder(Long id);

    StatusVo<ReservationTypeVo> reservationType();

    StatusOneVo<MoneyAndMasterVo> moneyAndMaster();
}
