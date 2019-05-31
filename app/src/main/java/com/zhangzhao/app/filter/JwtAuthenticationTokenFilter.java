package com.zhangzhao.app.filter;

import com.zhangzhao.app.util.JwtTokenUtil;
import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.vo.StatusVo;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by linyuan on 2017/12/11.
 */
public class JwtAuthenticationTokenFilter extends GenericFilterBean {

    private final Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    private JwtTokenUtil jwtTokenUtil;
    
    public JwtAuthenticationTokenFilter(JwtTokenUtil jwtTokenUtil) {
    	this.jwtTokenUtil=jwtTokenUtil;
	}

	@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
            HttpServletResponse response=(HttpServletResponse)servletResponse;
            StatusVo vo = new StatusVo();
            String duan_token = resolveToken(httpReq,0);
            String chang_token = resolveToken(httpReq,2);
            Authentication authentication = null;
			if ((duan_token==null && chang_token==null) || (chang_token==null && this.jwtTokenUtil.isTokenExpired(duan_token, 0))) {
				PrintWriter out = response.getWriter();
				vo.fail(ErrorCode.TOKEN_D_ERROR,"token is null");
				out.println(vo);
				out.flush();
				out.close();
        		return;
        	}else if (duan_token==null && this.jwtTokenUtil.isTokenExpired(chang_token, 2)){
                PrintWriter out = response.getWriter();
                vo.fail(ErrorCode.TOKEN_C_ERROR,"token is null");
                out.println(vo);
                out.flush();
                out.close();
                return;
            }else if(duan_token!=null && !this.jwtTokenUtil.isTokenExpired(duan_token, 0)){
                authentication = this.jwtTokenUtil.getUserFromToken(duan_token, 0);
                if (SecurityContextHolder.getContext().getAuthentication() == null){
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

            filterChain.doFilter(servletRequest, servletResponse);
        }catch (ExpiredJwtException e){
            log.info("Security exception for user {} - {}",
                    e.getClaims().getSubject(), e.getMessage());

            log.trace("Security exception trace: {}", e);
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    public static String resolveToken(HttpServletRequest request,int type){
    	String headerName = null;
    	if (type==0) {
    		headerName=JwtTokenUtil.REFRESH_TOKEN;
		}else if (type==2) {
			headerName=JwtTokenUtil.ACCESS_TOKEN;
		}
        String bearerToken = request.getHeader(headerName);
        String tokenHead = "Bearer ";
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenHead)){
            return bearerToken.substring(7);
        }
        return bearerToken;
    }
    
}
