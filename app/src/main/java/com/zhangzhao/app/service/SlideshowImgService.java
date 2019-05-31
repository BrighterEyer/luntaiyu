package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.MainVo;
import com.zhangzhao.app.vo.SlideshowImgVo;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;

public interface SlideshowImgService extends CommonService {

    StatusOneVo<MainVo> slideshowImgList(Integer page, Integer pageSize);
}
