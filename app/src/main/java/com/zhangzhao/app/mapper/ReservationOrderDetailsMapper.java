package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.ReservationOrderDetailsVo;
import com.zhangzhao.common.dto.ReservationDto;
import com.zhangzhao.common.entity.Reservation;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 预约详情
 *
 * @author Administrator
 */
@Component
@Mapper(componentModel = "spring")
public interface ReservationOrderDetailsMapper {

    Reservation dtoToBean(ReservationDto reservationDto);


    ReservationOrderDetailsVo beanToVo(Reservation reservation);
}
