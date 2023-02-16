package com.example.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@program: library
 *@description: 路由
 *@author: yubin
 *@create: 2023-02-17 02:03
 */
@Api(tags = "路由")
@Controller
public class RouteController {
    /**
     * 跳转登录
     */
    @ApiOperation("跳转登录页")
    @GetMapping({"/login","/","logout"})
    public String toLogin() {
        return "login";
    }

    /**
     * 跳转首页
     */
    @ApiOperation("跳转首页")
    @RequestMapping({"/index"})
    public String toIndex() {
        return "index";
    }
}