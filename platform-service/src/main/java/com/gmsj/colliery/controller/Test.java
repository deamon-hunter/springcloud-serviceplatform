package com.gmsj.colliery.controller;

import com.gmsj.colliery.clients.LoginClient;
import com.gmsj.core.business.command.base.LoginCommand;
import com.gmsj.core.business.model.RestResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/test")
@RestController
public class Test {
    @Autowired
    private LoginClient loginClient;

    @PostMapping("/in")
    RestResp login(LoginCommand command) {

        return loginClient.loginIn(command);
    }

}
