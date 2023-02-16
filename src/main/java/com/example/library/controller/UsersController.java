package com.example.library.controller;

import cn.hutool.core.date.DateUtil;
import com.example.library.entity.Users;
import com.example.library.exception.ExceptionHandle;
import com.example.library.service.UserService;
import com.example.library.util.CodeEnum;
import com.example.library.util.Constants;
import com.example.library.util.ConvertUtil;
import com.example.library.util.Result;
import com.example.library.vo.UserOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *@program: library
 *@description: 用户管理
 *@author: yubin
 *@create: 2023-02-16 16:06
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExceptionHandle exceptionHandle;

    @ApiOperation("用户详情")
    @GetMapping("/detail")
    public Result userDetail(Integer id) {
        Result result = new Result();
        try {
            Users user =  userService.userDetail(id);
            if(null!=user){
                UserOut out = new UserOut();
                BeanUtils.copyProperties(user,out);
                out.setBirth(DateUtil.format(user.getBirthday(), Constants.DATE_FORMAT));
                out.setIdent(ConvertUtil.identStr(user.getIdentity()));
                out.setPassword(null);
                result = Result.success(CodeEnum.SUCCESS,out);
            }else{
                result = Result.fail(CodeEnum.USER_NOT_FOUND);
            }
        } catch (BeansException e) {
            result = exceptionHandle.exceptionGet(e);
        }
        return result;
    }
}