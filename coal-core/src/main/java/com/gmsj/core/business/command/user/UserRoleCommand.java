package com.gmsj.core.business.command.user;

import com.gmsj.core.business.command.base.HeaderCommand;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UserRoleCommand extends HeaderCommand {

    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "角色id")
    private Long roleId;

}
