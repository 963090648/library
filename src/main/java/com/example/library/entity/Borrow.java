package com.example.library.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 *@program: library
 *@description: 借阅信息
 *@author: yubin
 *@create: 2023-02-15 17:26
 */
@Data
@TableName(value = "borrow") //访问h2数据库
public class Borrow {

    @Id
    @ApiModelProperty("主键ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("图书编码")
    @TableField(value = "user_id")
    private Long userId;

    @ApiModelProperty("图书ID")
    @TableField(value = "book_id")
    private Long bookId;

    @ApiModelProperty("借阅时间")
    @TableField(value = "create_time")
    private Date createTime;

    @ApiModelProperty("归还时间")
    @TableField(value = "end_time")
    private Date endTime;

    @ApiModelProperty("实际归还时间")
    @TableField(value = "update_time")
    private Date updateTime;

    @ApiModelProperty("是否归还? 0 已归还/1 未归还")
    @TableField(value = "ret")
    private Integer ret;
    // json: {"userId":userId,"bookId":bookId,"createTime":createTime,"endTime":endTime,"updateTime":updateTime}
}
