package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.MoneyAndMasterVo;
import com.zhangzhao.app.vo.ReservationVo;
import com.zhangzhao.common.dto.ReservationDto;
import com.zhangzhao.common.entity.MasterTime;
import com.zhangzhao.common.entity.Reservation;
import com.zhangzhao.common.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

/**
 * 预约
 *
 * @author Administrator
 */
@Component
@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationVo beanToVo(Reservation reservation);

    @Mappings({
            @Mapping(target = "userId", source = "userId"),
            @Mapping(target = "status", source = "status"),
    })
    Reservation dtoToBean(ReservationDto reservationDto, Long userId, int status);

    @Mappings({
            @Mapping(target = "orderMoney", source = "user.orderMoney"),
            @Mapping(target = "gotoWork", source = "masterTime.gotoWork"),
            @Mapping(target = "getoffWork", source = "masterTime.getoffWork")
    })
    MoneyAndMasterVo moneyToVo(User user, MasterTime masterTime);

}
