package com.gmsj.user.controller;

import com.gmsj.core.business.model.RestResp;
import com.gmsj.user.service.UserService;
import com.gmsj.core.business.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 用户详情操作接口
 *
 * @author hongQiang tang
 * @version $Id: UserController.java, v 0.1 2017年6月20日 下午5:04:35 Administrator Exp $
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/profile/{userId}")
    public UserVO getUserProfile(@PathVariable Long userId) {
        return userService.getUserProfile(userId);
    }

    @GetMapping("/info/{openId}")
    public UserVO getUserInfoByOpenId(@PathVariable String openId) {
        return userService.getUserInfoByOpenId(openId);
    }

    @PostMapping("/pass")
    public RestResp setNewPassword(@RequestBody UserVO userVO) {
         userService.setNewPassword(userVO.getId(), userVO.getPassword());
        return RestResp.ok("设置成功");
    }

}
