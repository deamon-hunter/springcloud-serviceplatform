package com.gmsj.user.service.impl;

import com.gmsj.thridparty.service.ShortMessageService;
import com.gmsj.user.mapper.UserMapper;
import com.gmsj.user.model.User;
import com.gmsj.user.service.AuthService;
import com.gmsj.user.service.LoginLogService;
import com.gmsj.user.service.LoginService;
import com.gmsj.user.service.UserService;
import com.google.common.collect.ImmutableMap;
import com.gmsj.core.business.vo.user.AuthInfoVO;
import com.gmsj.core.business.vo.user.UserVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * 用户登录业务接口实现类
 *
 * @author hongQiang tang
 * @version $Id: LoginServiceImpl.java, v 0.1 2017年6月19日 下午2:04:58 Administrator Exp $
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;
    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private ShortMessageService shortMessageService;

    @Override
    public UserVO loginByPhone(UserVO userVO) {


        shortMessageService.verifyCode(userVO.getPhone(), userVO.getVerifyCode());

        // 查询目标用户
        User user = userMapper.getUserByPhone(userVO.getPhone());

        // 是否是新用户
        // 如果是数据库没有的用户，将自动为其注册到系统中
        if (user == null) {
            user = userService.register(userVO);
            userVO = new UserVO();
            userVO.setIsNewUser(true);
        } else {
            userVO = new UserVO();
            // 用户已经存在
            // 判断用户是否被冻结或者删除，如果已经被删除则不
            Assert.isTrue(user.getStatus(), "用户已被冻结");
        }
        return executeLoginAction(user, userVO);
    }

    @Override
    public UserVO loginByAccount(UserVO userVO) {



        // 查询目标用户
        User user = new User();
        user.setPhone(userVO.getPhone());
        user = userMapper.selectOne(user);

        // 登录信息检测
        Assert.notNull(user, "用户不存在");
        Assert.state(Objects.equals(DigestUtils.md5Hex(userVO.getPassword()), user.getPassword()), "密码输入错误");
        Assert.isTrue(user.getStatus(), "用户已被冻结");

        return executeLoginAction(user, userVO);
    }

    private UserVO executeLoginAction(User user, UserVO userVO) {


        // 查询用户辅助信息
        AuthInfoVO authInfoVO = authService.getAuthInfo(user.getId());

        // 保存登录日志信息
        loginLogService.saveLoginLog(userVO);
        userVO.setAuthInfo(authInfoVO);

        userVO.setApiKey(JWTUtil.produceJWTToken(ImmutableMap.of("userId", user.getId())));
        userVO.setOpenId(null);
        return userVO;
    }
}
