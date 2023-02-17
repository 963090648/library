package com.example.library.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 */
@Data
public class PageOut {

    @ApiModelProperty("当前页")
    private Long currPage;

    @ApiModelProperty("每页条数")
    private Long pageSize;

    @ApiModelProperty("总数")
    private Long total;

    @ApiModelProperty("数据")
    private Object list;
}
