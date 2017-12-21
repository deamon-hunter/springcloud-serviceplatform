package com.gmsj.user.controller;

import com.gmsj.core.business.model.RestResp;
import com.gmsj.user.clients.IndexClient;
import com.gmsj.core.business.group.user.NewPwd;
import com.gmsj.core.business.group.user.Register;
import com.gmsj.core.business.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 公用的一些操作接口
 * <p>
 * <ul>
 * <li>发送手机验证码</li>
 * </ul>
 */
@RestController
@RequestMapping("/")
public class IndexController {


    @Autowired
    private IndexClient indexClient;

    /**
     * 注销用户并退出登录
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public void doLogout() {
    }

    /**
     * 发送手机短信验证码
     * 目前开发阶段使用随机验证码模拟效果，上线阶段使用真正的短信运营接口发送短信
     *
     * @param vo 表单参数
     * @return
     */
    @RequestMapping(value = "verify-code", method = RequestMethod.POST)
    public RestResp<Map> generationVerifyCode(@RequestBody UserVO vo) {
        // Map { verifyCode : 456464 }
        return indexClient.generationVerifyCode(vo);
    }

    /**
     * 用户注册系统
     *
     * @param userVO 表单参数
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public void doRegister(@RequestBody @Validated(Register.class) UserVO userVO) {

        indexClient.registionUser(userVO);
    }

    /**
     * 忘记密码 + 重置密码
     *
     * @param userVO 表单参数
     */
    @RequestMapping(value = "forget-pwd", method = RequestMethod.PUT)
    public void forgetPassword(@RequestBody @Validated(Register.class) UserVO userVO) {
        indexClient.forgetPassword(userVO);
    }

    /**
     * 初次设置密码操作
     *
     * @param userVO 表单参数
     */
    @RequestMapping(value = "pwd-new", method = RequestMethod.PUT)
    public void setNewPassword(@RequestBody @Validated(NewPwd.class) UserVO userVO) {
        indexClient.setNewPassword(userVO);
    }
}
