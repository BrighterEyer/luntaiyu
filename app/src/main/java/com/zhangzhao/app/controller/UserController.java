package com.zhangzhao.app.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.cache.SmsCodeCache;
import com.zhangzhao.app.filter.JwtAuthenticationTokenFilter;
import com.zhangzhao.app.security.MyUserDetails;
import com.zhangzhao.app.util.JwtTokenUtil;
import com.zhangzhao.app.vo.StatisticsPercentageVO;
import com.zhangzhao.app.vo.StatisticsVO;
import com.zhangzhao.app.vo.UserPaySettingVo;
import com.zhangzhao.app.vo.UserPersonalVo;
import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.dto.MasterTimeDto;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.util.FileTool;
import com.zhangzhao.common.util.SMSCode;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.common.vo.TokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Api(tags = "User", description = "用户")
@RestController
@RequestMapping("/app/user")
public class UserController extends BaseService {

    @Autowired
    SmsCodeCache SmsCodeCache;

    @GetMapping("/q/send/text/messages")
    @ApiOperation(value = "1 发送短信", notes = "发送短信", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo sendTextMessages(@ApiParam(value = "手机号", required = true) @RequestParam String phone) {
        StatusVoidVo vo = new StatusVoidVo();
        SmsCodeCache.sendSmsCode(phone, "您好");
        vo.success();
        return vo;
    }

    @PostMapping("/q/update/password")
    @ApiOperation(value = "3 修改密码", notes = "修改密码", produces = "application/json")
    public StatusVoidVo updatePassword(@ApiParam(value = "手机号", required = true) @RequestParam String phone,
                                       @ApiParam(value = "验证码123456", required = true) @RequestParam String code,
                                       @ApiParam(value = "密码", required = true) @RequestParam String password,
                                       @ApiParam(value = "1-密码 2-支付密码", required = true) @RequestParam int type) {
        SMSCode smsCode = SmsCodeCache.getSmsCode(phone);
        StatusVoidVo vo = SmsCodeCache.verificationCode(phone, code, smsCode);
        try {
            User user = userService.findByPhone(phone);
            if (user == null) {
                vo.fail(ErrorCode.ACCOUNT_ERROR, "用户不存在");
                return vo;
            }
            if (vo.getCode().equals("200")) {
                if (type == 1) {
                    userService.updatePassword(user.getId(), password);
                } else if (type == 2) {
                    user.setPaymentPassword(password);
                    userService.saveBean(user);
                }
                vo.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            vo.fail(ErrorCode.SYSTEM_ERROR, "错误,请重新注册");
        }
        return vo;
    }

    @PostMapping("/q/role/selection")
    @ApiOperation(value = "4 注册", notes = "注册", produces = MediaType.APPLICATION_JSON_VALUE)
    public String roleSelection(@ApiParam(value = "手机号", required = true) @RequestParam("phone") String phone,
                                @ApiParam(value = "验证码123456", required = true) @RequestParam String code,
                                @ApiParam(value = "密码", required = true) @RequestParam("password") String password) {
        SMSCode smsCode = SmsCodeCache.getSmsCode(phone);
        StatusVoidVo vo = SmsCodeCache.verificationCode(phone, code, smsCode);
        StatusOneVo statusVo = new StatusOneVo();
        try {
            if (vo.getCode().equals("200")) {
                User bean = userService.findByPhone(phone);
                if (bean != null) {
                    statusVo.fail(ErrorCode.USER_EXISTENCE_ERROR, "手机号已经注册");
                    return statusVo.toString();
                }
                User user = new User();
                user.setName(phone);
                user.setPhone(phone);
                user.setPassword(password);
                userService.saveUser(user);
                HashMap<String, Object> json = Maps.newHashMap();
                json.put("password", password);
                statusVo.success(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
            vo.fail(ErrorCode.SYSTEM_ERROR, "错误,请重新注册");
        }
        return statusVo.toString();
    }

    @PostMapping("/logout")
    @ApiOperation(value = " 5 退出登入", notes = "退出登入")
    public StatusVoidVo logoutPage(HttpServletRequest request, HttpServletResponse response) {
        StatusVoidVo vo = new StatusVoidVo();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            userService.useType(getUser().getId(), 0);
            new SecurityContextLogoutHandler().logout(request, response, auth);
            vo.success();
        }
        return vo;
    }

    @PostMapping("/q/login")
    @ApiOperation(value = " 6 登录", notes = "手机号登录", produces = "application/json")
    public StatusOneVo<TokenVo> login(@ApiParam(value = "手机号", required = true) @RequestParam("phone") String phone,
                                      @ApiParam(value = "密码", required = true) @RequestParam("password") String password,
                                      HttpServletResponse response) {
        return userService.login(phone, password, response);
    }

    @PostMapping("/q/update/bind/phone")
    @ApiOperation(value = "7 修改/绑定注册手机", notes = "修改/绑定注册手机", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo updateBindPhone(@ApiParam(value = "第三方登录id") @RequestParam String threeId,
                                        @ApiParam(value = "手机号", required = true) @RequestParam String phone,
                                        @ApiParam(value = "验证码", required = true) @RequestParam String code) {
        SMSCode smsCode = SmsCodeCache.getSmsCode(phone);
        StatusVoidVo vo = SmsCodeCache.verificationCode(phone, code, smsCode);
        try {
            if (vo.getCode().equals("200")) {
                userService.updateBindPhone(getUser().getId(), phone);
            }


        } catch (Exception e) {
            e.printStackTrace();
            vo.fail(ErrorCode.SYSTEM_ERROR, "错误,请重试");

        }
        return vo;
    }

    @ApiOperation(value = "8 选择用户类型", notes = "选择用户类型", produces = "application/json")
    @GetMapping("/q/user/choice/type/id")
    public StatusVoidVo findByType(@ApiParam(value = "类型 1-用户2-师傅", required = true) @RequestParam int type) {
        StatusVoidVo vo = new StatusVoidVo();
        User user = getUser();
        if (type == 1) {
            user.setUserModel(1);
        } else if (type == 2) {
            user.setMasterModel(2);
        }
        userService.saveUser(user);
        vo.success();
        return vo;
    }

    @ApiOperation(value = "9 头像保存", notes = "头像保存", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/q/user/save/icon")
    public StatusVoidVo saveIcon(@ApiParam(value = "头像", required = true) @RequestParam String icon) {
        return userService.saveIcon(getUser().getId(), icon);
    }

    @PostMapping("/q/user/verification/phone/eq")
    @ApiOperation(value = "10 手机验证码匹配", notes = "手机验证码匹配", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo verificationPhone(@ApiParam(value = "手机号", required = true) @RequestParam String phone, @ApiParam(value = "验证码", required = true) @RequestParam String
            code) {
        SMSCode smsCode = SmsCodeCache.getSmsCode(phone);
        StatusVoidVo vo = SmsCodeCache.verificationCode(phone, code, smsCode);
        return vo;
    }

    @GetMapping("/master/s/apply")
    @ApiOperation(value = "11 师傅申请", notes = "师傅申请", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo masterApply(@ApiParam(value = "请求参数封装") @RequestBody @Valid MasterTimeDto masterTimeDto, BindingResult bindingResult) {
        return userService.masterApply(masterTimeDto, bindingResult);
    }

    @GetMapping("/personal/s/information")
    @ApiOperation(value = "12 个人信息", notes = "个人信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<UserPersonalVo> personal() {
        StatusOneVo vo = new StatusOneVo();
        UserPersonalVo userPersonalVo = new UserPersonalVo();
        Optional<User> optional = userService.findById(getUser().getId());
        User user = optional.get();
        userPersonalVo.setIntegral(Integer.toString(user.getIntegral()));
        BeanUtils.copyProperties(optional.get(), userPersonalVo);
        vo.success(userPersonalVo);
        return vo;
    }

    @GetMapping("/update/personal/s/information")
    @ApiOperation(value = "13 修改个人信息", notes = "修改个人信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo updatePersonal(@ApiParam(value = "头像") @RequestParam(required = false) String icon,
                                       @ApiParam(value = "性别 0-男 1-女") @RequestParam(required = false) String gender,
                                       @ApiParam(value = "昵称") @RequestParam(required = false) String name,
                                       @ApiParam(value = "车型") @RequestParam(required = false) String vehicle,
                                       @ApiParam(value = "车重") @RequestParam(required = false) String vehicleWeight,
                                       @ApiParam(value = "路况") @RequestParam(required = false) String road) {
        User user = getUser();
        if (StringUtils.isNotBlank(icon)) {
            user.setIcon(icon);
        }
        if (StringUtils.isNotBlank(gender)) {
            user.setGender(gender);
        }
        if (StringUtils.isNotBlank(name)) {
            user.setName(name);
        }
        if (StringUtils.isNotBlank(vehicle)) {
            user.setVehicle(vehicle);
        }
        if (StringUtils.isNotBlank(vehicleWeight)) {
            user.setVehicleWeight(vehicleWeight);
        }
        if (StringUtils.isNotBlank(road)) {
            user.setRoadCondition(road);
        }
        return userService.saveBean(user);
    }


    @PostMapping(value = "/img/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation(value = " 图片上传接口", notes = "上传图片功能")
    public StatusOneVo upload(HttpServletRequest request) throws Exception {
        StatusOneVo vo = new StatusOneVo<>();
        List<String> list = Lists.newArrayList();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile multipartFile = multiRequest.getFile(iter.next());
                if ("".equals(multipartFile) || multipartFile == null || multipartFile.getSize() <= 0) {
                    vo.fail(ErrorCode.PARAMETER_ERROR, "参数错误");
                    return vo;
                } else {
                    InputStream ins = multipartFile.getInputStream();
                    String fileNames = FileTool.upload(ins, multipartFile.getOriginalFilename(), uploadConfig.getHouseFileUploadPath());
                    list.add(uploadConfig.getHouseFileSavePath() + "/" + fileNames);
                }
            }
            vo.success(list);
        }
        return vo;
    }

    @ApiOperation(value = "14 修改经纬度", notes = "修改经纬度", responseContainer = "0无 1正在接单", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/q/updatejwdu/id")
    public String updatejwdu(@ApiParam(value = "经度", required = true) @RequestParam double longitude,
                             @ApiParam(value = "纬度", required = true) @RequestParam double latitude) {
        return userService.updatejwdu(longitude, latitude).toString();
    }

    @Autowired
    private JwtTokenUtil tokenProvider;

    @ApiOperation(value = "15 根据长token(Authorization)，生成短的token并返回", notes = "根据长token，生成短的token并返回", produces = "application/json")
    @GetMapping("/user/new/token")
    public StatusOneVo<TokenVo> newToken(HttpServletRequest request, HttpServletResponse response) {
        TokenVo tokenVo = new TokenVo();
        StatusOneVo<TokenVo> vo = new StatusOneVo<>();
        String token = JwtAuthenticationTokenFilter.resolveToken(request, 2);
        if (tokenProvider.validateToken(token, 2)) {
            Authentication authentication = tokenProvider.getAuthentication(token, 2);
            try {
                MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
                if (myUserDetails == null || myUserDetails.getUser() == null) {
                    vo.fail(ErrorCode.ACCOUNT_ERROR, "用户不存在");
                    return vo;
                } else {
                    String tokenNext = tokenProvider.token(myUserDetails.getUser(), response, 0);
                    tokenVo.setTokenNext(tokenNext);
                    tokenVo.setToken(token);
                    vo.success();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return vo;
    }

    @GetMapping("/q/order/statistics")
    @ApiOperation(value = "16 数据统计", notes = "数据统计")
    public StatusOneVo<StatisticsVO> statistics() {
        return userService.statistics();
    }

    @GetMapping("/q/order/statistics/Percentage")
    @ApiOperation(value = "17 好评/准时百分比", notes = "好评/准时百分比")
    public StatusOneVo<StatisticsPercentageVO> StatisticsPercentage() {
        return userService.StatisticsPercentage();
    }

    @GetMapping("/q/present/ranking")
    @ApiOperation(value = "18 当前排名", notes = "当前排名")
    public StatusOneVo presentRanking() {
        return userService.preantranking(getUser().getId());

    }

    @GetMapping("/personal/s/setting")
    @ApiOperation(value = "19 支付密码查询", notes = "查询用户是否设置支付密码", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo selectDefray() {
        StatusOneVo vo = new StatusOneVo();
        UserPaySettingVo userPersonalVo = new UserPaySettingVo();
        Optional<User> optionalUser = userService.findById(getUser().getId());
        if (!optionalUser.isPresent()) {
            vo.fail(ErrorCode.USER_EXISTENCE_ERROR, "用户不存在");
            return vo;
        }
        User user = optionalUser.get();
        if (StringUtils.isNotBlank(user.getPaymentPassword())) {
            vo.success(userPersonalVo.setPaymentPassword("1"));
        } else {
            vo.success(userPersonalVo.setPaymentPassword("0"));
        }
        return vo;
    }
}
