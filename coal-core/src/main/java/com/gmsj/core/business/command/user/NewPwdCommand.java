/**
 * 创建人： Hehaiyang
 * 包名字： com.gmsj.core.business.command.user
 * 创建时间：2017/12/26
 * 描述：TODO
 * Copyright @ 2014-2017 bicitech.cn
 */
package com.gmsj.core.business.command.user;

import com.gmsj.core.business.command.base.HeaderCommand;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *创建人： Hehaiyang 
 *包名字： com.gmsj.core.business.command.user
 *创建时间：2017/12/26
  *类名：NewPwdCommand
 *描述：修改密码command
 *Copyright @ 2014-2017 
 */
@Data
public class NewPwdCommand extends HeaderCommand {

    @NotNull(message = "账号不能为空")
    private String userName;

    @NotNull(message = "原密码不能为空")
    private String password;

    @NotNull(message = "新密码不能为空")
    private String newPassword;
}
