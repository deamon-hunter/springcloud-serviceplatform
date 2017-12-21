package com.gmsj.user.controller;

import com.gmsj.core.business.group.user.LoginByPass;
import com.gmsj.core.business.group.user.LoginByPhone;
import com.gmsj.user.service.LoginService;
import com.gmsj.core.business.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录操作
 *
 * @author hongQiang tang
 * @version $Id: LoginController.java, v 0.1 2017年6月19日 下午1:55:05 Administrator Exp $
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/account")
    public UserVO loginByAccount(@RequestBody @Validated(LoginByPass.class) UserVO userVO) {

        return loginService.loginByAccount(userVO);
    }

    @PostMapping("/phone")
    public UserVO loginByPhone(@RequestBody @Validated(LoginByPhone.class) UserVO userVO) {


        return loginService.loginByPhone(userVO);
    }
}
