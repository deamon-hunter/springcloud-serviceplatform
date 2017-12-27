package com.gmsj.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UserRoleVO {
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "角色id")
    private Long roleId;

}
