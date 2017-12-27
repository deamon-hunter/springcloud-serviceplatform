package com.gmsj.core.business.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserVO {
    @ApiModelProperty(value = "用户id")
    private Long userId;
    
    @ApiModelProperty(value = "用户名")
    private String username;
    
    @ApiModelProperty(value = "用户真实姓名")
    private String name;
    
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "机构id")
    private Long deptId;
    @ApiModelProperty(value = "机构名称")
    private String deptName;
    
    @ApiModelProperty(value = "邮箱")
    private String email;
    
    @ApiModelProperty(value = "手机号")
    private String mobile;
    
    @ApiModelProperty(value = "状态 0:禁用，1:正常")
    private Integer status;
    
    @ApiModelProperty(value = "创建用户id")
    private Long userIdCreate;
    
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;
    
    @ApiModelProperty(value = "角色id")
    private List<Long> roleIds;

}
