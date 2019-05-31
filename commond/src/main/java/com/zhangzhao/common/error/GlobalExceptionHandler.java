package com.zhangzhao.common.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorInfo jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ErrorInfo r = new ErrorInfo();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        //r.setMessage("系统异常");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
}

