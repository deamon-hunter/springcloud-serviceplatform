package com.gmsj.core.business.command.user;

import com.gmsj.core.business.command.base.HeaderCommand;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleMenuCommand extends HeaderCommand {
    @ApiModelProperty(value = "主键id")
	private Long id;
    @ApiModelProperty(value = "角色id")
	private Long  roleId;
    @ApiModelProperty(value = "菜单id")
	private Long menuId;

}
