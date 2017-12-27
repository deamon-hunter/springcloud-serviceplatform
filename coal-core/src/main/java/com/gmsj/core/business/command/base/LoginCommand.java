/**
 * 创建人： Hehaiyang
 * 包名字： com.gmsj.core.business.command.user
 * 创建时间：2017/12/26
 * 描述：TODO
 * Copyright @ 2014-2017 bicitech.cn
 */
package com.gmsj.core.business.command.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *创建人： Hehaiyang 
 *包名字： com.gmsj.core.business.command.user
 *创建时间：2017/12/26
  *类名：LoginCommand
 *描述：登陆用command对象
 *Copyright @ 2014-2017 
 */
@Data
public class LoginCommand extends  HeaderCommand{

    @NotNull(message = "账号不能为空")
    private String userName;
    /**
     * 新商盟密码
     */
    @NotNull(message = "密码不能为空")
    private String password;
}
