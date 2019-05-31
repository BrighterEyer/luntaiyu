package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.RankingService;
import com.zhangzhao.common.vo.StatusVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 活动
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RankingServiceImpl extends BaseService implements RankingService {

    @Override
    public StatusVo<String> ranking(int type) {
        StatusVo<String> vo = new StatusVo<>();
        if (type == 1) {
            List<String> monthRanking = rankingRepository.monthRanking();
            vo.success(monthRanking);
        } else if (type == 2) {
            List<String> quarterRanking = rankingRepository.quarterRanking();
            vo.success(quarterRanking);
        } else if (type == 3) {
            List<String> yearRanking = rankingRepository.yearRanking();
            vo.success(yearRanking);
        }
        return vo;
    }
}
