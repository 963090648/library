package com.example.library.util;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *@program: library
 *@description: 返回数据格式
 *@author: yubin
 *@create: 2023-02-16 16:09
 */
public class Result implements Serializable {

    @ApiModelProperty("响应码")
    private Integer code;

    @ApiModelProperty("响应信息")
    private String msg;

    @ApiModelProperty("响应数据")
    private Object data;

    public Result() {
    }

    public Result(String msg, String data) {
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public Result(CodeEnum codeEnum, Object data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getData();
        this.data = data;
    }

    public Result(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getData();
    }


    public static Result success(CodeEnum codeEnum, Object data) {
        return new Result(codeEnum,data);
    }

    public static Result success(CodeEnum codeEnum) {
        return new Result(codeEnum);
    }

    public static Result fail(CodeEnum codeEnum) {
        return new Result(codeEnum);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}