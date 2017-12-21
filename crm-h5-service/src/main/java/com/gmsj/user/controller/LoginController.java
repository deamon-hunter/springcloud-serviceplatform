package com.gmsj.user.controller;

import com.gmsj.core.business.model.RestResp;
import com.gmsj.user.clients.LoginClient;
import com.gmsj.core.business.group.user.LoginByPass;
import com.gmsj.core.business.group.user.LoginByPhone;
import com.gmsj.core.business.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ovrille
 * @date 2017/10/17
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginClient loginClient;

    @PostMapping("/account")
    public RestResp loginByAccount(@RequestBody @Validated(LoginByPass.class) UserVO userVO) {

        return loginClient.loginByAccount(userVO);
    }

    @PostMapping("/phone")
    public RestResp loginByPhone(@RequestBody @Validated(LoginByPhone.class) UserVO userVO) {

        return loginClient.loginByPhone(userVO);
    }




}
