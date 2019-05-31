package com.zhangzhao.common.vo;


import com.google.gson.Gson;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class StatusVoidVo {

    @ApiModelProperty(notes = "代码", required = true)
    private String code;

    @ApiModelProperty(value = "状态码")
    private String status;

    @ApiModelProperty(value = "状态值")
    private String msg;

    public StatusVoidVo() {
        this.code = "0";
        this.status = "0";
        this.msg = "失败";
    }

    public void success() {
        this.code = "200";
        this.status = "1";
        this.msg = "成功";
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
