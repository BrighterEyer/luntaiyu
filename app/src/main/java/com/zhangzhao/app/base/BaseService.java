package com.zhangzhao.app.base;

import com.zhangzhao.app.filter.JwtAuthenticationTokenFilter;
import com.zhangzhao.app.mapper.*;
import com.zhangzhao.app.service.*;
import com.zhangzhao.app.util.JwtTokenUtil;
import com.zhangzhao.common.commonservice.CommonServiceImpl;
import com.zhangzhao.common.config.UploadConfig;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

public class BaseService extends CommonServiceImpl {


    /**
     * 关注
     */
    @Autowired
    public AttentionService AttentionService;
    @Autowired
    public AttentionRepository AttentionRepository;
    @Autowired
    public AttentionMapper attentionMapper;

    /**
     * 收藏
     */
    @Autowired
    public CollectService collectService;
    @Autowired
    public CollectRepository collectRepository;
    @Autowired
    public CollectMapper collectMapper;
    /**
     * 评论
     */
    @Autowired
    public CommentsService commentsService;
    @Autowired
    public CommentsRepository commentsRepository;
    @Autowired
    public CommentsMapper commentsMapper;
    /**
     * 兑换记录
     */
    @Autowired
    public ForRecordService forRecordService;
    @Autowired
    public ForRecordRepository forRecordRepository;
    @Autowired
    public ForRecordMapper forRecordMapper;
    /**
     * 商品分类
     */
    @Autowired
    public GoodsClassificationService goodsClassificationService;
    @Autowired
    public GoodsClassificationRepository goodsClassificationRepository;
    @Autowired
    public GoodsClassificationMapper goodsClassificationMapper;
    /**
     * 商品信息
     */
    @Autowired
    public GoodsCommodityService goodsCommodityService;
    @Autowired
    public GoodsCommodityRepository goodsCommodityRepository;
    @Autowired
    public GoodsCommodityMapper goodsCommodityMapper;
    /**
     * 商品保障
     */
    @Autowired
    public GoodSecurityService goodSecurityService;
    @Autowired
    public GoodSecurityRepository goodSecurityRepository;
    @Autowired
    public GoodSecurityMapper goodSecurityMapper;
    /**
     * 师傅上下班
     */
    @Autowired
    public MasterTimeService masterTimeService;
    @Autowired
    public MasterTimeRepository masterTimeRepository;
    @Autowired
    public MasterTimeMapper masterTimeMapper;
    /**
     * 会员
     */
    @Autowired
    public MemberService memberService;
    @Autowired
    public MemberRepository memberRepository;
    @Autowired
    public MemberMapper memberMapper;
    /**
     * 预约评价
     */
    @Autowired
    public PrecontractEvaluateService precontractEvaluateService;
    @Autowired
    public PrecontractEvaluateRepository precontractEvaluateRepository;
    @Autowired
    public PrecontractEvaluateMapper precontractEvaluateMapper;

    /**
     * 预约类型
     */
    @Autowired
    public ReservationTypeMapper reservationTypeMapper;
    @Autowired
    public ReservationtypeRepository reservationtypeRepository;


    /**
     * 打卡
     */
    @Autowired
    public PunchCardService punchCardService;
    @Autowired
    public PunchCardRepository punchCardRepository;
    @Autowired
    public PunchCardMapper punchCardMapper;
    /**
     * 预约
     */
    @Autowired
    public ReservationService reservationService;
    @Autowired
    public ReservationRepository reservationRepository;
    @Autowired
    public ReservationMapper reservationMapper;


    /**
     * 退换货
     */
    @Autowired
    public ReturnPolicyService returnPolicyService;
    @Autowired
    public ReturnPolicyRepository returnPolicyRepository;
    @Autowired
    public ReturnPolicyMapper returnPolicyMapper;

    /**
     * 用户
     */
    @Autowired
    public UserService userService;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserMapper userMapper;
    /**
     * 排名
     */
    @Autowired
    public RankingRepository rankingRepository;
    @Autowired
    public RankingService rankingService;
    @Autowired
    public RankingMapper rankingMapper;

    /**
     * 用户搭档
     */
    @Autowired
    public UserCooperateService userCooperateService;
    @Autowired
    public UserCooperateRepository userCooperateRepository;
    @Autowired
    public UserCooperateMapper userCooperateMapper;
    /**
     * 我的钱包
     */
    @Autowired
    public WalletService walletService;
    @Autowired
    public WalletRepository walletRepository;
    @Autowired
    public WalletMapper walletMapper;
    /**
     * 提现
     */
    @Autowired
    public WithdrawService withdrawService;
    @Autowired
    public WithdrawRepository withdrawRepository;
    @Autowired
    public WithdrawMapper withdrawMapper;

    @Autowired
    public TradingRecordService tradingRecordService;
    @Autowired
    public TradingRecordRepository tradingRecordRepository;
    @Autowired
    public TradingRecordMapper tradingRecordMapper;

    /**
     * 活动
     */
    @Autowired
    public ActivityService activityService;
    @Autowired
    public ActivityRepository activityRepository;
    @Autowired
    public ActivityMapper activityMapper;

    /**
     * 地址
     */
    @Autowired
    public AddressService addressService;
    @Autowired
    public AddressRepository addressRepository;
    @Autowired
    public AddressMapper addressMapper;

    /**
     * 银行卡
     */
    @Autowired
    public BankCardService bankCardService;
    @Autowired
    public BankCardRepository bankCardRepository;
    @Autowired
    public BankCardMapper bankCardMapper;

    /**
     * 车型
     *
     * @return
     */
    @Autowired
    public CarModelRepository carModelRepository;

    /**
     * 投诉
     */
    @Autowired
    public ComplaintsService complaintsService;
    @Autowired
    public ComplaintsRepository complaintsRepository;
    @Autowired
    public ComplaintsMapper complaintsMapper;

    /**
     * 简介
     */
    @Autowired
    public CompanyProfileService companyProfileService;
    @Autowired
    public CompanyProfileRepository companyProfileRepository;
    @Autowired
    public CompanyProfileMapper companyProfileMapper;

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
     * 积分记录
     */
    @Autowired
    public IntegralService integralService;
    @Autowired
    public IntegralRepository integralRepository;
    @Autowired
    public IntegralMapper integralMapper;

    /**
     * 发票
     */
    @Autowired
    public InvoiceService invoiceService;
    @Autowired
    public InvoiceRepository invoiceRepository;
    @Autowired
    public InvoiceMapper invoiceMapper;

    /**
     * 物流
     */
    @Autowired
    public LogisticsRepository logisticsRepository;

    /**
     * 消息
     */
    @Autowired
    public MessagesService messagesService;
    @Autowired
    public MessagesRepository messagesRepository;
    @Autowired
    public MessagesMapper messagesMapper;

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
     * 订单详情
     */
    @Autowired
    public OrderDetailsMapper orderDetailsMapper;
    @Autowired
    public OrderDetailsRepository orderDetailsRepository;

    /**
     * 预约订单详情
     */
    @Autowired
    public ReservationOrderDetailsRepository reservationOrderDetailsRepository;
    @Autowired
    public ReservationOrderDetailsMapper reservationOrderDetailsMapper;

    /**
     * 质量处理
     */
    @Autowired
    public QualityService qualityService;
    @Autowired
    public QualityRepository qualityRepository;
    @Autowired
    public QualityMapper qualityMapper;

    /**
     * 服务
     */
    @Autowired
    public ServicesRepository servicesRepository;

    /**
     * 购物车
     */
    @Autowired
    public ShoppingCartService shoppingCartService;
    @Autowired
    public ShoppingCartRepository shoppingCartRepository;
    @Autowired
    public ShoppingCartMapper shoppingCartMapper;

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
     * 门店
     */
    @Autowired
    public StoreMapper storeMapper;
    @Autowired
    public StoreService storeService;
    @Autowired
    public StoreRepository storeRepository;

    /**
     * 礼品
     */
    @Autowired
    public PresentMapper presentMapper;
    @Autowired
    public PresentRepository presentRepository;
    @Autowired
    public PresentService presentService;

    /**
     * 流程
     */
    @Autowired
    public FlowService flowService;
    @Autowired
    public FlowRepository flowRepository;
    @Autowired
    public FlowMapper flowMapper;

    /**
     * 属性
     */
    @Autowired
    public PropertiesRepository propertiesRepository;

    @Autowired
    public JwtTokenUtil jwtTokenUtil;
    @Autowired
    public UploadConfig uploadConfig;

    public static BaseService baseService;

    @PostConstruct
    public void init() {
        baseService = this;
        baseService.jwtTokenUtil = this.jwtTokenUtil;
    }

    public static User getUser() {
        String duan_token = JwtAuthenticationTokenFilter.resolveToken(getRequest(), 0);
        if (!baseService.jwtTokenUtil.isTokenExpired(duan_token, 0)) {
            return (User) baseService.jwtTokenUtil.getUserFromToken(duan_token, 0).getPrincipal();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal != null) {
            return ((User) principal);
        }
        return null;
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
}
