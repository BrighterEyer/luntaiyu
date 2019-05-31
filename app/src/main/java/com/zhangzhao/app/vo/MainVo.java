package com.zhangzhao.app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class MainVo {

    @ApiModelProperty(value = "轮播图")
    private List<SlideshowImgVo> collect;

    @ApiModelProperty(value = "分类")
    private List<GoodsClassificationVo> goodsClassificationVos;

    @ApiModelProperty(value = "活动图片")
    private SlideshowImgVo slideshowImgVo;

    @ApiModelProperty(value = "0-没有活动")
    private String endTime;

    @ApiModelProperty(value = "热销商品")
    private List<GoodsCommodityVo> goodsCommodityVoList;
}
