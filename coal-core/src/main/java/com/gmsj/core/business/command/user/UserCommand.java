package com.gmsj.core.business.command.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gmsj.core.business.command.base.HeaderCommand;
import com.gmsj.core.business.group.auth.RealName;
import com.gmsj.core.business.group.bank.Bind;
import com.gmsj.core.business.group.user.NewPwd;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
public class UserCommand extends HeaderCommand {
    @ApiModelProperty(value = "用户id")
    private Long userId;
    
    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名称不能为空")
    private String userName;
    
    @ApiModelProperty(value = "用户真实姓名")
    private String name;
    
    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空", groups = {RealName.class})
    private String password;

    @ApiModelProperty(value = "机构id")
    private Long deptId;

    @JsonIgnore
    @ApiModelProperty(value = "机构名称")
    private String deptName;
    
    @ApiModelProperty(value = "邮箱")
    private String email;
    
    @ApiModelProperty(value = "手机号")
    //@Pattern(message = "身份证不合法", regexp = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)")
    @Pattern(message = "手机号码不符合规范", regexp = "^1[34578]\\d{9}$", groups = {Bind.class})
    //@NotNull(message = "电话不能为空", groups = {Bind.class})
    private String mobile;
    
    @ApiModelProperty(value = "状态 0:禁用，1:正常")
    private Integer status;
    
    @ApiModelProperty(value = "创建用户id")
    private Long userIdCreate;
    
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;

}
