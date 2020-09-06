package com.ssafy.devit.model;

import io.swagger.annotations.ApiModelProperty;

public class CommonResponse {
    @ApiModelProperty(value = "msg", position = 1)
    public String msg;
    @ApiModelProperty(value = "result", position = 2)
    public Object result;
}
