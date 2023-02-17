package com.example.library.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *@program: library
 *@description: 用户管理
 *@author: yubin
 *@create: 2023-02-16 16:06
 */
@Data
public class BackOut extends BookOut{

    @ApiModelProperty("借阅时间")
    private String borrowTime;

    @ApiModelProperty("应还时间")
    private String endTime;

    @ApiModelProperty("是否逾期")
    private String late;

}
