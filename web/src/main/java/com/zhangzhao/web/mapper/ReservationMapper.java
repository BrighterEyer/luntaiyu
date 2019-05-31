package com.zhangzhao.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.common.entity.Reservation;
import com.zhangzhao.web.vo.ReservationVo;

/**
 * 预约
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface ReservationMapper {
	ReservationVo beanToVo(Reservation reservation);

}
