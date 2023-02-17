package com.example.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.persistence.*;
import java.util.Date;

/**
 *@program: library
 *@description: 图书信息
 *@author: yubin
 *@create: 2023-02-15 17:26
 */
@Data
@TableName(value = "book") //访问h2数据库
public class Book {

    @Id
    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("图书ISBN编码")
    @TableField(value = "isbn")
    private String isbn;

    @ApiModelProperty("图书名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty("图书作者")
    @TableField(value = "author")
    private String author;

    @ApiModelProperty("图书页数")
    @TableField(value = "pages")
    private Integer pages;

    @ApiModelProperty("翻译")
    @TableField(value = "translate")
    private String translate;

    @ApiModelProperty("出版社")
    private String publish;

    @ApiModelProperty("单价")
    @TableField(value = "price")
    private Double price;

    @ApiModelProperty("库存")
    @TableField(value = "size")
    private Integer size;

    @ApiModelProperty("分类")
    @TableField(value = "type")
    private String type;

    @ApiModelProperty("出版时间")
    @TableField(value = "publishTime")
    private Date publishTime;

}
