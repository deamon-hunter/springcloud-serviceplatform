package com.gmsj.user.controller;

import com.gmsj.core.business.group.user.NewPwd;
import com.gmsj.core.business.group.user.Register;
import com.gmsj.thridparty.service.ShortMessageService;
import com.gmsj.user.service.UserService;
import com.gmsj.core.business.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private UserService userService;


    @Autowired
    private ShortMessageService shortMessageService;

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
    public void generationVerifyCode(@RequestBody UserVO vo) {

        Assert.notNull(vo.getPhone(), "手机号码不能为空");

        shortMessageService.sendVerifyCode(vo.getPhone());

    }

    /**
     * 用户注册系统
     *
     * @param userVO 表单参数
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public void doRegister(@RequestBody @Validated(Register.class) UserVO userVO) {
        shortMessageService.verifyCode(userVO.getPhone(), userVO.getVerifyCode());

        userService.register(userVO);
    }

    /**
     * 忘记密码 + 重置密码
     *
     * @param userVO 表单参数
     */
    @RequestMapping(value = "forget-pwd", method = RequestMethod.PUT)
    public void forgetPassword(@RequestBody @Validated(Register.class) UserVO userVO) {
        shortMessageService.verifyCode(userVO.getPhone(), userVO.getVerifyCode());
        userService.resetPassword(userVO.getPhone(), userVO.getPassword());
    }

    /**
     * 初次设置密码操作
     *
     * @param userVO 表单参数
     */
    @RequestMapping(value = "pwd-new", method = RequestMethod.PUT)
    public void setNewPassword(@RequestBody @Validated(NewPwd.class) UserVO userVO) {
        userService.setNewPassword(userVO.getId(), userVO.getPassword());
    }
}
