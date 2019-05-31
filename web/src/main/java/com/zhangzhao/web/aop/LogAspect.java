package com.zhangzhao.web.aop;

import com.zhangzhao.common.entity.admin.Admin;
import com.zhangzhao.common.entity.log.AdminLog;
import com.zhangzhao.common.repository.AdminLogRepository;
import com.zhangzhao.common.repository.AdminRepository;
import com.zhangzhao.web.security.AdminUser;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Autowired
    AdminLogRepository adminLogRepository;
    @Around("execution(* com.zhangzhao.web.controller.*.*(..))")
    public Object aroundManagerLogPoint(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        if(method.isAnnotationPresent(ApiOperation.class)){
            ApiOperation annotation = method.getAnnotation(ApiOperation.class);
            if (annotation.position()>0){
                AdminLog adminLog = new AdminLog();
                adminLog.setContent(annotation.value());
                adminLog.setCreateTime(new Date());
                adminLog.setType(annotation.position());
                AdminUser adminUser = (AdminUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                Admin admin = new Admin();
                BeanUtils.copyProperties(adminUser,admin);
                adminLog.setAdmin(admin);
                adminLogRepository.save(adminLog);
            }
        }
        return pjp.proceed();
    }
}
