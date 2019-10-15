package com.example.auth.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class JWTUser implements Serializable {

    private static final long serialVersionUID = 4606962766319025196L;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户No")
    private String userNo;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "手机号")
    private String mobile;

}
