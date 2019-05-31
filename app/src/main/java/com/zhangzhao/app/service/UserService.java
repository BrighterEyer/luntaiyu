package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.StatisticsPercentageVO;
import com.zhangzhao.app.vo.StatisticsVO;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.MasterTimeDto;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.common.vo.TokenVo;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface UserService extends CommonService {
    /**
     * 用户登录
     *
     * @return
     */
    StatusOneVo<TokenVo> login(String phone, String password, HttpServletResponse response);

    User findByPhone(String phone);

    /**
     * 忘记密码
     *
     * @param id
     * @param password
     */
    void updatePassword(Long id, String password);


    /**
     * 修改注册手机
     *
     * @param id
     * @param phone
     */
    void updateBindPhone(Long id, String phone);

    /**
     * 师傅申请
     *
     * @param masterTimeDto
     * @param bindingResult
     * @return
     */
    StatusVoidVo masterApply(MasterTimeDto masterTimeDto, BindingResult bindingResult);

    /**
     * 保存头像
     *
     * @param id
     * @param icon
     * @return
     */
    StatusVoidVo saveIcon(Long id, String icon);

    /**
     * 修改经纬度
     *
     * @param longitude
     * @param latitude
     * @return
     */
    StatusOneVo updatejwdu(double longitude, double latitude);


    Optional<User> findById(Long id);

    /**
     * 用户登出
     *
     * @param id
     * @return
     */
    void useType(Long id, int useType);

    /**
     * 数据统计
     *
     * @return
     */
    StatusOneVo<StatisticsVO> statistics();

    /**
     * 百分比
     *
     * @return
     */
    StatusOneVo<StatisticsPercentageVO> StatisticsPercentage();

    /**
     * 当前排名
     *
     * @return
     */
    StatusOneVo preantranking(Long masterId);

    void saveUser(User user);

    Optional<User> lnquire(Long id);
}
