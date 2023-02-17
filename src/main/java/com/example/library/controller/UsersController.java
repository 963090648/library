package com.example.library.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.entity.Users;
import com.example.library.exception.ExceptionHandle;
import com.example.library.service.UserService;
import com.example.library.util.CodeEnum;
import com.example.library.util.Constants;
import com.example.library.util.ConvertUtil;
import com.example.library.util.Result;
import com.example.library.vo.PageIn;
import com.example.library.vo.PageOut;
import com.example.library.vo.UserOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @ApiOperation("添加用户")
    @PostMapping("/add")
    public Result addUsers(@RequestBody Users users) {
        return Result.success(CodeEnum.SUCCESS,userService.addUser(users));
    }

//    @ApiOperation("添加用户")
//    @PostMapping("/add")
//    public R addUsers(@RequestBody Users users) {
//        return R.success(CodeEnum.SUCCESS,userService.addUser(users));
//    }

    @ApiOperation("添加读者")
    @PostMapping("/addReader")
    public Result addReader(@RequestBody Users users) {
        if (users == null) {
            return Result.fail(CodeEnum.PARAM_ERROR);
        }
        // 读者默认是普通用户
        users.setIsAdmin(1);
        return Result.success(CodeEnum.SUCCESS,userService.addUser(users));
    }

    @ApiOperation("添加管理员")
    @PostMapping("/addAdmin")
    public Result addAdmin(@RequestBody Users users) {
        if (users == null) {
            return Result.fail(CodeEnum.PARAM_ERROR);
        }
        // 设置管理员权限
        users.setIsAdmin(users.getIdentity());
        return Result.success(CodeEnum.SUCCESS,userService.addUser(users));
    }


    @ApiOperation("编辑用户")
    @PostMapping("/update")
    public Result modifyUsers(@RequestBody Users users) {
        return Result.success(CodeEnum.SUCCESS,userService.updateUser(users));
    }

    @ApiOperation("删除用户")
    @GetMapping("/delete")
    public Result delUsers(Integer id) {
        userService.deleteUser(id);
        return Result.success(CodeEnum.SUCCESS);
    }

    @ApiOperation("用户列表")
    @PostMapping("/list")
    public Result getUsers(@RequestBody PageIn pageIn) {
        if (pageIn == null) {
            return Result.fail(CodeEnum.PARAM_ERROR);
        }
        // 封装分页出参对象
        IPage<Users> userList = userService.getUserList(new Page<>(pageIn.getCurrPage(), pageIn.getPageSize()),pageIn);
        PageOut pageOut = new PageOut();
        pageOut.setCurrPage(userList.getCurrent());
        pageOut.setPageSize(userList.getPages());
        pageOut.setTotal( userList.getTotal());
        List<UserOut> outs = new ArrayList<>();
        for (Users users : userList.getRecords()) {
            UserOut out = new UserOut();
            BeanUtils.copyProperties(users,out);
            out.setIdent(ConvertUtil.identStr(users.getIdentity()));
            out.setBirth(DateUtil.format(users.getBirthday(),Constants.DATE_FORMAT));
            outs.add(out);
        }
        pageOut.setList(outs);
        return Result.success(CodeEnum.SUCCESS,pageOut);
    }

    @ApiOperation("用户详情")
    @GetMapping("/detail")
    public Result userDetail(Long id) {
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

    @ApiOperation("获取当前用户登陆信息")
    @GetMapping("/currUser")
    public Result getCurrUser() {
        Result result = new Result();
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal!=null) {
                Map<String,Object> map = BeanUtil.beanToMap(principal);
                String username = (String) map.get("username");
                if (StrUtil.isNotBlank(username)) {
                    Users users = userService.findByUsername(username);
                    UserOut out = new UserOut();
                    BeanUtils.copyProperties(users,out);
                    out.setBirth(DateUtil.format(users.getBirthday(),Constants.DATE_FORMAT));
                    Integer identity = users.getIdentity();
                    String ident = "";
                    if (identity == Constants.STUDENT) {
                        ident = Constants.STU_STR;
                    }else if (identity == Constants.TEACHER) {
                        ident = Constants.TEA_STR;
                    }else if (identity == Constants.OTHER) {
                        ident = Constants.OTHER_STR;
                    }else if (identity == Constants.ADMIN) {
                        ident = Constants.ADMIN_STR;
                    }
                    out.setIdent(ident);
                    result = Result.success(CodeEnum.SUCCESS,out);
                }
            }else{
                result = Result.fail(CodeEnum.USER_NOT_FOUND);
            }
        } catch (BeansException e) {
            result = exceptionHandle.exceptionGet(e);
        }
        return result;
    }
}