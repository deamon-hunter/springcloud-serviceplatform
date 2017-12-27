package com.gmsj.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class RoleVO {
    @ApiModelProperty(value = "角色id")
	private Long roleId;
    @ApiModelProperty(value = "角色名称")
	private String roleName;
    @ApiModelProperty(value = "机构id")
    private Long deptId;
    @ApiModelProperty(value = "机构名称")
    private String deptName;
    @ApiModelProperty(value = "。。。。。")
	private String roleSign;
    @ApiModelProperty(value = "备注")
	private String remark;
    @ApiModelProperty(value = "创建工号id")
	private Long userIdCreate;
    @ApiModelProperty(value = "创建时间")
	private Timestamp gmtCreate;
    @ApiModelProperty(value = "修改时间")
	private Timestamp gmtModified;
    @ApiModelProperty(value = "菜单权限")
	private List<Long> menuIds;

}
