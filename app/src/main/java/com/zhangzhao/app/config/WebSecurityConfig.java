package com.zhangzhao.app.config;

import com.zhangzhao.app.filter.JwtAuthenticationTokenFilter;
import com.zhangzhao.app.security.MyUserDetailsService;
import com.zhangzhao.app.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/*.pn*", "/**/*.cs*", "/**/*.js*", "/**/*.jp*", "/**/*.htm*", "/**/*.ic*")
                .antMatchers( "/app/*/q/**", "/app/*/s/**",  "/swagger-ui.html/**", "/webjars/**", "/swagger-resources/**", "/v2/api-docs");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义获取用户信息
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置请求访问策略
        http.cors().disable()
            .csrf().disable()
            //由于使用Token，所以不需要Session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            //验证Http请求
            .authorizeRequests()
            //其它任何请求都要经过认证通过
            .anyRequest().authenticated()
            .and()
            //设置登出
            .logout().logoutUrl("/app/user/logout").invalidateHttpSession(true).permitAll()
            .and().anonymous().disable().exceptionHandling();
        //添加JWT filter 在
        http.addFilterAfter(genericFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public GenericFilterBean genericFilterBean() {
        return new JwtAuthenticationTokenFilter(jwtTokenUtil);
    }


    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
