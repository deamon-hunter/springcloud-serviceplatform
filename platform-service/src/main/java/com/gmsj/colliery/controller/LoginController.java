package com.gmsj.colliery.controller;

import com.gmsj.colliery.clients.LoginClient;
import com.gmsj.core.business.command.base.LoginCommand;
import com.gmsj.core.business.model.RestResp;
import com.gmsj.core.business.vo.user.LoginTokenDataVO;
import com.gmsj.core.lib.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/test")
@RestController
public class LoginController {
    @Autowired
    private LoginClient loginClient;

    @PostMapping("/in")
    @Command
    RestResp<LoginTokenDataVO> login(@RequestBody LoginCommand command) {

        return loginClient.loginIn(command);
    }

}
