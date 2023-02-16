package com.example.library.exception;

import com.example.library.util.CodeEnum;
import com.example.library.util.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@program: library
 *@description: 异常类定义
 *@author: yubin
 *@create: 2023-02-16 18:12
 */
@ControllerAdvice
public class ExceptionHandle extends RuntimeException{

    /**
     * 判断错误是否是已定义的已知错误，不是则由未知错误代替，同时记录在log中
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionGet(Exception e){
        if(e instanceof DescribeException){
            DescribeException MyException = (DescribeException) e;
            return new Result(MyException.getCode(),MyException.getMessage(),"");
        }
        return Result.fail(CodeEnum.FAIL);
    }
}