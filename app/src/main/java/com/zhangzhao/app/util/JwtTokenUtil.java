package com.zhangzhao.app.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zhangzhao.app.security.MyUserDetails;
import com.zhangzhao.app.security.MyUserDetailsService;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.repository.UserRepository;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * JWT工具类
 */
@Component
public class JwtTokenUtil {

    private final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);
    /**
     * 密钥
     */
    private String secretKey;

    //失效日期
    private long tokenValidityInMilliseconds;
    //1小时
    private long secondIn1day;
    //（记住我）失效日期
    private long tokenValidityInMillisecondsForRememberMe;

    public static final String AUTHORITIES_KEY = "auth";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";

    private static final int DUAN_TYPE = 0;
    private static final int CHANG_TYPE = 2;

    private static final String USER = "USER";

    @PostConstruct
    public void init() {
        this.secretKey = "zhangzhaoappToken";
        this.secondIn1day = 1000 * 60 * 60;
        this.tokenValidityInMilliseconds = secondIn1day * 24;
        this.tokenValidityInMillisecondsForRememberMe = secondIn1day * 24 * 7L;
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        Date validity = null;
        if (Integer.valueOf(claims.get("type").toString()) == DUAN_TYPE) {
            validity = new Date(now + this.secondIn1day * 2);
            secretKey = REFRESH_TOKEN;
        } else if ((Integer.valueOf(claims.get("type").toString())) == CHANG_TYPE) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
            secretKey = ACCESS_TOKEN;
        }
        return Jwts.builder().setClaims(claims).setExpiration(validity).signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token, int type) {
        Claims claims = null;
        try {
            if (type == DUAN_TYPE) {
                secretKey = REFRESH_TOKEN;
            } else if (type == CHANG_TYPE) {
                secretKey = ACCESS_TOKEN;
            }
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("{}","token过期，"+token);
        }
        return claims;
    }

    /**
     * 生成令牌
     *
     * @param user 用户
     * @return 令牌
     */
    public String generateToken(User user, int type) {
        Map<String, Object> claims = new HashMap<>(3);
        if (type == DUAN_TYPE) {
            secretKey = REFRESH_TOKEN;
        } else if (type == CHANG_TYPE) {
            secretKey = ACCESS_TOKEN;
        }
        claims.put("sub", user.getName());
        claims.put("created", new Date());
        claims.put(USER, user);
        claims.put("type", type);
        return generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token, int type) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token, type);
            username = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    /**
     * 从令牌中获取用户
     *
     * @param token 令牌
     * @return 用户
     */
    public Authentication getUserFromToken(String token, int type) {
        User user = null;
        try {
            Claims claims = getClaimsFromToken(token, type);
            user = (JSONObject.parseObject(new Gson().toJson(claims.get(USER)), User.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UsernamePasswordAuthenticationToken(user, null, null);
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token, int type) {
        try {
            Claims claims = getClaimsFromToken(token, type);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token, int type) {
        String refreshedToken = null;
        try {
            Claims claims = getClaimsFromToken(token, type);
            claims.put("created", new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return refreshedToken;
    }

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    //获取用户权限
    public Authentication getAuthentication(String token, int type) {
        System.out.println("token:" + token);
        if (type == DUAN_TYPE) {
            secretKey = REFRESH_TOKEN;
        } else if (type == CHANG_TYPE) {
            secretKey = "zhangzhaoappToken";
        }
        Claims claims = Jwts.parser()                           //解析Token的payload
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        MyUserDetails principal = myUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(principal, null, null);
    }

    //验证Token是否正确
    public boolean validateToken(String token, int type) {
        try {
            if (type == DUAN_TYPE) {
                secretKey = REFRESH_TOKEN;
            } else if (type == CHANG_TYPE) {
                secretKey = "zhangzhaoappToken";
            }
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);   //通过密钥验证Token
            return true;
        } catch (io.jsonwebtoken.SignatureException e) {                                     //签名异常
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {                                 //JWT格式错误
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {                                   //JWT过期
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {                               //不支持该JWT
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {                              //参数错误异常
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

    @Autowired
    UserRepository userRepository;

    /**
     * 写人token
     *
     * @param httpResponse
     * @return
     * @throws Exception
     */
    public String token(User user, HttpServletResponse httpResponse, int type) throws Exception {
        try {
            //通过用户名和密码创建一个 Authentication 认证对象，实现类为 UsernamePasswordAuthenticationToken
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
            //如果认证对象不为空
            if (Objects.nonNull(authenticationToken)) {
                userRepository.findByName(authenticationToken.getPrincipal().toString()).orElseThrow(() -> new Exception("用户不存在"));
            }
            //将 Authentication 绑定到 SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //生成Token
            String token = generateToken(user, type);
            //将Token写入到Http头部
            if (type == DUAN_TYPE) {
                httpResponse.addHeader(REFRESH_TOKEN, "Bearer " + token);
            } else if (type == CHANG_TYPE) {
                httpResponse.addHeader(ACCESS_TOKEN, "Bearer " + token);
            }
            return "Bearer " + token;
        } catch (BadCredentialsException authentication) {
            authentication.printStackTrace();
        }
        return "密码错误";
    }

}