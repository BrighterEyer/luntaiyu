package com.zhangzhao.common.vo;


import com.google.gson.Gson;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

import java.util.List;

@ApiModel
public class StatusPageVo<T> {

    @ApiModelProperty(notes = "代码", required = true)
    private String code;

    @ApiModelProperty(value = "状态码")
    private String status;

    @ApiModelProperty(value = "状态值")
    private String msg;

    @ApiModelProperty(value = "总记录数")
    private Long total;

    @ApiModelProperty(value = "数据列表")
    private List<T> data;

    public StatusPageVo() {
        this.code = "0";
        this.status = "0";
        setMsg("失败");
    }

    public void success(String msg) {
        this.code = "200";
        this.status = "200";
        if (StringUtils.isBlank(msg)) {
            this.msg = "成功";
        } else {
            this.msg = msg;
        }
    }

    public void setStatusVo(String code, String status, String msg, List<T> data) {
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.total = total;
    }

    public void success(List<T> data, long total) {
        this.code = "200";
        this.status = "200";
        this.msg = "成功";
        this.total = total;
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
