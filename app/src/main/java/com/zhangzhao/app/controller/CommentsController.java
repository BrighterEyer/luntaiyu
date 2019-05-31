package com.zhangzhao.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.OrderDetailsVo;
import com.zhangzhao.common.entity.Comments;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Comments", description = "评论")
@RestController
@RequestMapping("/app/comments")
public class CommentsController extends BaseService {

    @ApiOperation(value = "1 订单商品评论", notes = "订单商品评论", produces = "application/json")
    @GetMapping(value = "/comments/s/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<OrderDetailsVo> product(@ApiParam(value = "订单id") @RequestParam Long id) {
        return commentsService.product(id);
    }

    @ApiOperation(value = "2 商品评论", notes = "商品评论", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/comments/s/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo saveBean(@ApiParam(value="评价类容jsonarray[{goods_commodity_id:'商品id',commcontent:'内容',imgs:'图片',type:'星',fuCommentsId:'父id'}]",
            required = true)@RequestParam String context){
        try {
            List<Comments> comments = JSONArray.parseArray(context, Comments.class);
            comments=comments.parallelStream().map(o->o.setUser(getUser())).collect(Collectors.toList());
            return commentsService.saveList(comments);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
