package com.zhangzhao.web.base;

import com.zhangzhao.common.commonservice.CommonServiceImpl;
import com.zhangzhao.common.config.UploadConfig;
import com.zhangzhao.common.repository.*;
import com.zhangzhao.web.mapper.*;
import com.zhangzhao.web.security.AdminUser;
import com.zhangzhao.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseService extends CommonServiceImpl {

    /**
     * 用户
     */
    @Autowired
    public AdminService adminService;
    @Autowired
    public AdminRepository adminRepository;
    @Autowired
    public AdminMapper adminMapper;
    @Autowired
    public UserService userService;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserMapper userMapper;
    @Autowired
    public AuthorityService authorityService;
    @Autowired
    public AuthorityRepository authorityRepository;

    /**
     * 轮播图
     */
    @Autowired
    public SlideshowImgService slideshowImgService;
    @Autowired
    public SlideshowImgRepository slideshowImgRepository;
    @Autowired
    public SlideshowImgMapper slideshowImgMapper;
    /**
     * 优惠券
     */
    @Autowired
    public CouponService couponService;
    @Autowired
    public CouponRepository couponRepository;
    @Autowired
    public CouponMapper couponMapper;

    /**
     * 订单
     */
    @Autowired
    public OrderSupplyService orderSupplyService;
    @Autowired
    public OrderSupplyRepository orderSupplyRepository;
    @Autowired
    public OrderSupplyMapper orderSupplyMapper;

    /**
     * 分类
     */
    @Autowired
    public GoodsClassificationService goodsClassificationService;
    @Autowired
    public GoodsClassificationRepository goodsClassificationRepository;
    @Autowired
    public GoodsClassificationMapper goodsClassificationMapper;
    /**
     * 出库信息
     */
    @Autowired
    public OutDeliveryService outDeliveryService;
    @Autowired
    public OutDeliveryRepository outDeliveryRepository;
    @Autowired
    public OutDeliveryMapper outDeliveryMapper;

    /**
     * 商品
     */
    @Autowired
    public GoodsCommodityService goodsCommodityService;
    @Autowired
    public GoodsCommodityRepository goodsCommodityRepository;
    @Autowired
    public GoodsCommodityMapper goodsCommodityMapper;

    /**
     * 属性
     */
    @Autowired
    public PropertiesService propertiesService;
    @Autowired
    public PropertiesRepository propertiesRepository;

    /**
     * 保障
     */
    @Autowired
    public GoodSecurityService goodSecurityService;
    @Autowired
    public GoodSecurityRepository goodSecurityRepository;

    @Autowired
    public UploadConfig uploadConfig;

    /**
     * 用户评价
     * @param request
     */
    @Autowired
    public CommentsService commentsService;
    @Autowired
    public CommentsRepository commentsRepository;
    @Autowired
    public CommentsMapper commentsMapper;

    @ModelAttribute
    public void setUrl(HttpServletRequest request) {
        request.setAttribute("url", request.getRequestURI());
    }

    public static AdminUser getAdmin() {
        AdminUser admin = (AdminUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return admin;
    }
}
