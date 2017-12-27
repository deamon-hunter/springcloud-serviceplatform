package com.gmsj.user.service;

import com.gmsj.core.business.command.base.LoginCommand;
import com.gmsj.core.business.command.user.NewPwdCommand;
import com.gmsj.user.vo.LoginTokenDataVO;

/**
 *创建人： Hehaiyang 
 *包名字： com.gmsj.user.controller
 *创建时间：2017/12/22
  *类名：UserService
 *描述：账号管理服务
 *Copyright @ 2014-2017 
 */
public interface UserService {

    LoginTokenDataVO authenticate(LoginCommand loginCommand);

    int modifyPwd(NewPwdCommand newPwdCommand);
}
