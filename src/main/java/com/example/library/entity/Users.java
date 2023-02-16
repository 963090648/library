package com.example.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 *@program: library
 *@description: 用户信息
 *@author: yubin
 *@create: 2023-02-15 17:26
 */
@Data
@TableName(value = "users") //访问h2数据库
public class Users {

    @Id
    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("地址")
    @TableField(value = "address")
    private String name;

    @ApiModelProperty("可借数量")
    @TableField(value = "size")
    private Integer size;

    @ApiModelProperty("身份：0 学生,1 教师,2 校外人士,3 管理员")
    @TableField(value = "identity")
    private Integer identity;

    @ApiModelProperty("是否为管理员")
    @TableField(value = "IS_ADMIN")
    private Integer isAdmin;

    @ApiModelProperty("生日")
    @TableField(value = "birthday")
    private Date birthday;

    @ApiModelProperty("头像")
    @TableField(value = "avatar")
    private String avatar;

    @ApiModelProperty("昵称")
    @TableField(value = "nickname")
    private String nickname;

    @ApiModelProperty("邮箱")
    @TableField(value = "email")
    private String email;

    @ApiModelProperty("密码")
    @TableField(value = "password")
    private String password;

    @ApiModelProperty("电话")
    @TableField(value = "tel")
    private String tel;

    @ApiModelProperty("用户名")
    @TableField(value = "username")
    private String username;
}
