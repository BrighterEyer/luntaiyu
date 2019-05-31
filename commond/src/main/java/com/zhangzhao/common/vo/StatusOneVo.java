package com.zhangzhao.common.vo;


import com.google.gson.Gson;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

@ApiModel
public class StatusOneVo<T> {

    @ApiModelProperty(notes = "代码", required = true)
    private String code;

    @ApiModelProperty(value = "状态码")
    private String status;

    @ApiModelProperty(value = "状态值")
    private String msg;

    @ApiModelProperty(value = "数据列表")
    private T data;

    public StatusOneVo() {
        this.code = "0";
        this.status = "0";
        setMsg("失败");
    }

    public void success() {
        this.code = "200";
        this.status = "1";
        if (StringUtils.isBlank(msg)) {
            this.msg = "成功";
        } else {
            this.msg = msg;
        }
    }

    public void success(T data) {
        this.code = "200";
        this.status = "1";
        this.msg = "成功";
        this.data = data;
    }

    public void fail(String code, String msg) {
        setCode(code);
        setMsg(msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
