package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.UserService;
import com.zhangzhao.app.vo.StatisticsPercentageVO;
import com.zhangzhao.app.vo.StatisticsVO;
import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.dto.MasterTimeDto;
import com.zhangzhao.common.entity.Reservation;
import com.zhangzhao.common.entity.Store;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.entity.Wallet;
import com.zhangzhao.common.util.BeanUtil;
import com.zhangzhao.common.util.NumberUtil;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.common.vo.TokenVo;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * 用户
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService implements UserService {
    /**
     * 用户登录
     *
     * @return
     */
    @Override
    public StatusOneVo<TokenVo> login(String phone, String password, HttpServletResponse response) {
        StatusOneVo<TokenVo> vo = new StatusOneVo<>();
        User user = userRepository.findByPhone(phone);
        if (user == null || !user.getPassword().equals(password)) {
            vo.fail(ErrorCode.ACCOUNT_PASSWORD_ERROR, "账号或密码错误");
            return vo;
        }
        try {
            TokenVo tokenVo = new TokenVo();
            String token = jwtTokenUtil.token(user, response, 2);
            tokenVo.setToken(token);
            token = jwtTokenUtil.token(user, response, 0);
            tokenVo.setTokenNext(token);
            tokenVo.setType(user.getType());
            tokenVo.setUserModel(user.getUserModel());
            tokenVo.setMasterModel(user.getMasterModel());
            vo.success(tokenVo);
        } catch (Exception e) {
            e.printStackTrace();
            vo.fail(ErrorCode.SYSTEM_ERROR, "系统异常");
        }
        return vo;

    }

    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public void updatePassword(Long id, String password) {
        userRepository.updatePassword(id, password);
    }

    @Override
    public void updateBindPhone(Long id, String phone) {
        userRepository.updateBindPhone(id, phone);
    }

    @Override
    public StatusVoidVo saveIcon(Long id, String icon) {
        StatusVoidVo vo = new StatusVoidVo();
        userRepository.saveIcon(id, icon);
        vo.success();
        return vo;
    }

    @Override
    public StatusVoidVo saveBean(Object object) {
        StatusVoidVo vo = new StatusVoidVo();
        userRepository.saveAndFlush((User) object);
        vo.success();
        return vo;
    }

    @Override
    public StatusVoidVo masterApply(MasterTimeDto masterTimeDto, BindingResult results) {
        StatusVoidVo vo = new StatusVoidVo();
        if (results.hasErrors()) {
            vo.fail(ErrorCode.PARAMETER_ERROR, results.toString());
        }
        User user = getUser();
        BeanUtil.copyByName(masterTimeDto, user);
        BeanUtils.copyProperties(masterTimeDto, user);
        user.setApplyStatus(User.ApplyStatus.apply.getApplyStatus());
        userRepository.saveAndFlush(user);
        if (masterTimeDto.getTypeMaster() == User.TypeMaster.SHOP.getTypeMaster() ||
                masterTimeDto.getTypeMaster() == User.TypeMaster.REPAIRFACTORY.getTypeMaster()) {
            Store store = storeMapper.mdtoToBean(masterTimeDto);
            store.setUserId(getUser().getId());
            storeRepository.save(store);
        }
        vo.success();
        return vo;
    }

    @Override
    public StatusOneVo updatejwdu(double longitude, double latitude) {
        StatusOneVo vo = new StatusOneVo();
        userRepository.updatejwdu(getUser().getId(), longitude, latitude);
        List<Reservation> reservationList = reservationRepository.findByIdAndStatus(getUser().getId(), new Integer[]{1, 2, 3});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 0);
        if (reservationList != null && reservationList.size() > 0) {
            jsonObject.put("status", 1);//正在接单
        }
        vo.success(jsonObject);
        return vo;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void useType(Long id, int useType) {
        userRepository.useType(id, useType);
    }

    @Override
    public StatusOneVo<StatisticsVO> statistics() {

        StatusOneVo<StatisticsVO> vo = new StatusOneVo();
        StatisticsVO statisticsVO = new StatisticsVO();
        //数据统计
        int orderFrequency = reservationRepository.selectByStatus(getUser().getId());//累计完成订单
        int daysOrderFrequency = reservationRepository.selectAllocateTimeDays(getUser().getId());//今日订单
        double addShouzhi = reservationRepository.selectAddShouzhi(getUser().getId());//累计收支
        double yesterdayShouzhi = reservationRepository.selectyesterdayShouzhi(getUser().getId());//昨日收支
        statisticsVO.setOrderFrequency(orderFrequency);
        statisticsVO.setDaysOrderFrequency(daysOrderFrequency);
        statisticsVO.setAddShouzhi(addShouzhi);
        statisticsVO.setYesterdayShouzhi(yesterdayShouzhi);
        vo.success(statisticsVO);
        return vo;
    }

    @Override
    public StatusOneVo<StatisticsPercentageVO> StatisticsPercentage() {
        StatusOneVo<StatisticsPercentageVO> vo = new StatusOneVo();
        StatisticsPercentageVO percentageVO = new StatisticsPercentageVO();
        Reservation reservation = new Reservation();
        //百分比统计
        int countStars = reservationRepository.countStars(getUser().getId());//好评数
        int zhunshishu = reservationRepository.zhunShiShu(getUser().getId());//准时数
        int count = reservationRepository.count(getUser().getId());//总订单数
        double haoPinglv = NumberUtil.formatDouble((double) countStars / count);//好评百分比
        double zhunShilv = NumberUtil.formatDouble((double) zhunshishu / count);//准时率
        percentageVO.setHaoPinglv(haoPinglv);
        percentageVO.setZhunShilv(zhunShilv);
        reservation.setHaoPinglv(haoPinglv);
        reservationRepository.save(reservation);
        vo.success(percentageVO);
        return vo;
    }

    @Override
    public StatusOneVo preantranking(Long masterId) {
        StatusOneVo vo = new StatusOneVo();
        int i = reservationRepository.preantranking(masterId);
        vo.success(i);
        return vo;
    }

    @Override
    public void saveUser(User user) {
        user = userRepository.saveAndFlush(user);
        Wallet wallet = new Wallet();
        wallet.setBalance(0d);
        wallet.setUserId(user.getId());
        wallet.setStatus(Wallet.Status.THROUGH.getStatus());
        walletRepository.save(wallet);

    }

    /**
     * 查询用户是否设置支付密码
     *
     * @param id
     * @return
     */
    @Override
    public Optional<User> lnquire(Long id) {
        return userRepository.findById(id);
    }


}
