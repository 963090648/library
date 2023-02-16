package com.example.library.exception;

import com.example.library.util.CodeEnum;

/**
 *@program: library
 *@description: 异常类处理
 *@author: yubin
 *@create: 2023-02-16 18:09
 */
public class DescribeException extends RuntimeException {

    private Integer code;

    /**
     * 继承exception，加入错误状态值
     * @param codeEnum
     */
    public DescribeException(CodeEnum codeEnum) {
        super(codeEnum.getData());
        this.code = codeEnum.getCode();
    }

    /**
     * 自定义错误信息
     * @param message
     * @param code
     */
    public DescribeException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}