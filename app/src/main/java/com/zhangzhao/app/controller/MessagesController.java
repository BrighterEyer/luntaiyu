package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.MessagesVo;
import com.zhangzhao.app.vo.ShoppingCartVo;
import com.zhangzhao.common.entity.User;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Messages",description = "消息")
@RestController
@RequestMapping("/app/messages")
public class MessagesController extends BaseService {

    @GetMapping("/shopping/cart/s/list")
    @ApiOperation(value = "1 用户消息列表", notes = "用户消息列表",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<MessagesVo> findAll(@ApiParam(value="当前页")@RequestParam(required = false,defaultValue = "1")Integer page,
                                        @ApiParam(value="页数")@RequestParam(required = false,defaultValue = "10")Integer pageSize){
        return messagesService.findAll(page,pageSize,null);
    }

    @GetMapping("/shopping/article/s/all")
    @ApiOperation(value = "2 用户消息条数", notes = "用户消息条数",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<MessagesVo> articleAll(){
        User user = getUser();
        return messagesService.articleNumber(user.getId());
    }
}
