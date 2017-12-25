/**
 * 创建人： Hehaiyang
 * 包名字： com.gmsj.user.controller
 * 创建时间：2017/12/22
 * 描述：TODO
 * Copyright @ 2014-2017 bicitech.cn
 */
package com.gmsj.user.controller;

import com.gmsj.core.conf.security.annotation.RoleCheck;
import com.gmsj.user.service.UserService;
import com.gmsj.user.vo.LoginTokenDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "用户信息模块", description = "登陆、验证、获取菜单信息等")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户登录", notes = "1", response = LoginTokenDataVO.class, httpMethod = "POST")
    @RequestMapping(path = "/authenticate", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public LoginTokenDataVO authenticate(@RequestBody @RequestParam(value = "userName") String userName , @RequestParam(value = "password")String password) {
        return userService.authenticate(userName,password);
    }


    @ApiOperation(value = "修改密码", notes = "3",  httpMethod = "POST")
    @RoleCheck
    @RequestMapping(path = "/modifyPwd", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public void modifyPwd(@RequestParam(value = "userName") String userName ,
                          @RequestParam(value = "password") String password, @RequestParam(value = "newPassword") String newPassword) {
        userService.modifyPwd(userName,password,newPassword);
    }


}
