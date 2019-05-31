package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.RankingVO;
import com.zhangzhao.common.entity.Ranking;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 排行
 */
@Component
@Mapper(componentModel = "spring")
public interface RankingMapper {

    RankingVO beanToVo(Ranking ranking);
}
