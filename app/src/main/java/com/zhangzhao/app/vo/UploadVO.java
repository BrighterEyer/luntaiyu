/**
 * Copyright (C), 2018
 * FileName: UploadVO
 * Author:   Administrator
 * Date:     2018/10/8 16:59
 * Description:
 */
package com.zhangzhao.app.vo;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2018/10/8
 */
public class UploadVO {

    List<String> fileNames = new ArrayList<>();
    Map<String, String> params = new HashMap<>();

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
