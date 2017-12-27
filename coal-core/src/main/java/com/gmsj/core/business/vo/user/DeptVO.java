package com.gmsj.core.business.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class DeptVO {
    @ApiModelProperty(value = "部门id")
    private Long deptId;
    @ApiModelProperty(value = "上级部门ID，一级部门为0")
    private Long parentId;
    @ApiModelProperty(value = "部门名称")
    private String name;
    @ApiModelProperty(value = "排序")
    private Integer orderNum;
    @ApiModelProperty(value = "是否删除  -1：已删除  0：正常")
    private Integer delFlag;
    @ApiModelProperty(value = "所属系统组")
    private Long sysGroupId;
    @ApiModelProperty(value = "所属系统组名称")
    private String sysGroupName;
    @ApiModelProperty(value = "所属系统组编码")
    private String sysGroupCode;

}
