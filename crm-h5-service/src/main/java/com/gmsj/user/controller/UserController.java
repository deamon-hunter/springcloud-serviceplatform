package com.gmsj.user.controller;

import com.gmsj.core.business.model.RestResp;
import com.gmsj.user.clients.UserClient;
import com.gmsj.util.SessionUtil;
import com.gmsj.core.business.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ovrille
 * @date 2017/10/16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserClient userClient;


    @GetMapping("/info/{openId}")
    public RestResp getUserInfoByOpenId(@PathVariable("openId") String openId) {

        return userClient.getUserInfoByOpenId(openId);
    }


    @PostMapping("/pass")
    public RestResp setNewPassword(@RequestBody UserVO userVO) {
        userVO.setId(SessionUtil.getCurrUid());
        return userClient.setNewPassword(userVO);
    }


}
