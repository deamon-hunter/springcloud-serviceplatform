package com.gmsj.core.business.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleMenuVO {
    @ApiModelProperty(value = "主键id")
	private Long id;
    @ApiModelProperty(value = "角色id")
	private Long  roleId;
    @ApiModelProperty(value = "菜单id")
	private Long menuId;

}
