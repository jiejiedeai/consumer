package com.mq.consumer.controller;

import com.mq.consumer.config.CustomerException;
import com.mq.consumer.model.JsonResult;
import com.mq.consumer.service.UserService;
import com.mq.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test/")
@Api(tags = "测试")
public class TestController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    @PostMapping("searchUsers")
    public JsonResult<List<User>> searchUsers() throws CustomerException {
        return JsonResult.success(userService.searchUsers());
    }

}
