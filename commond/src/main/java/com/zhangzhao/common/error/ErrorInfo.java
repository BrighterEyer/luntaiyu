package com.zhangzhao.common.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ErrorInfo {
    public static final Integer OK = 0;
    public static final Integer ERROR = 1000003;

    private Integer code;
    private String message;
    private String url;
    private Date date = new Date();

}
