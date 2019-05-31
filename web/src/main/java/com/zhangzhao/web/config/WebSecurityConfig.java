package com.zhangzhao.web.config;

import com.zhangzhao.web.security.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zzq
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/*.pn*", "/**/*.css", "/**/*.js", "/**/*.jp*", "/**/*.html", "/**/*.ico");
    }

    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests() //           每个子匹配器将会按照声明的顺序起作用。
                .antMatchers("/resources/**", "/index.html").permitAll() // 任何用户可以访问
                .anyRequest().authenticated()       // 任何没有匹配上的其他的url请求，只需要用户被验证。
                .and()
                .formLogin()
                .defaultSuccessUrl("/web/admin/s/index", true) // 验证成功跳转
                .loginPage("/login")
                .usernameParameter("name")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
