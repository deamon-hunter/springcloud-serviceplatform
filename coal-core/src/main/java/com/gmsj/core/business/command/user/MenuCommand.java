package com.gmsj.core.business.command.user;

import com.gmsj.core.business.command.base.HeaderCommand;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MenuCommand extends HeaderCommand {
    @ApiModelProperty(value = "菜单id")
	private Long menuId;
	 
    @ApiModelProperty(value = "父菜单ID，一级菜单为0")
	private Long parentId;
	
    @ApiModelProperty(value = "部门id")
    private Long deptId;
    @ApiModelProperty(value = "部门名称")
    private String deptName;
     
    @ApiModelProperty(value = "菜单名称")
	private String name;
	 
    @ApiModelProperty(value = "菜单URL")
	private String url;
	
    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
	private String perms;
	 
    @ApiModelProperty(value = "类型 0：目录 1：菜单 2：按钮")
	private Integer type;
	 
    @ApiModelProperty(value = "菜单图标")
	private String icon;
	 
    @ApiModelProperty(value = "排序")
	private Integer orderNum;
	 
    @ApiModelProperty(value = "创建时间")
	private Date gmtCreate;
	 
    @ApiModelProperty(value = "修改时间")
	private Date gmtModified;

}
