package com.gmsj.user.service.impl;

import com.gmsj.core.business.command.base.LoginCommand;
import com.gmsj.core.business.command.user.NewPwdCommand;
import com.gmsj.core.conf.security.Session;
import com.gmsj.core.conf.security.TokenService;
import com.gmsj.core.util.MD5Utils;
import com.gmsj.user.mapper.UserMapper;
import com.gmsj.user.model.User;
import com.gmsj.user.service.UserService;
import com.gmsj.user.vo.LoginTokenDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public LoginTokenDataVO authenticate(LoginCommand loginCommand) {

        Example example = new Example( User.class );
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo( "userName", loginCommand.getUserName() );
        List<User> users =userMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(users)) {
            throw new RuntimeException("未找到登陆用户");
        }
        User user =users.get(0);
        String passwordN = MD5Utils.encrypt(loginCommand.getUserName(),loginCommand.getPassword());
        if (! passwordN.equalsIgnoreCase(user.getPassword())) {
            throw new RuntimeException("密码验证不通过");
        }
        //创建session
        final Session session = Session.buildSession(user.getUserId(),user.getUserName());
        //生成token
        String token = tokenService.generate(session);
        LoginTokenDataVO loginDataVO = new LoginTokenDataVO();

        loginDataVO.setUserName(user.getUserName());
        loginDataVO.setUserId(user.getUserId());
        loginDataVO.setAuthorToken(token);

        return loginDataVO;
    }

    @Override
    @Transactional
    public int modifyPwd(NewPwdCommand newPwdCommand) {
        Example example = new Example( User.class );
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo( "userName", newPwdCommand.getUserName() );
        List<User> users =userMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(users)) {
            throw new RuntimeException("未找到登陆用户");
        }
        User user = users.get(0);

        String passwordN = MD5Utils.encrypt(newPwdCommand.getUserName(),newPwdCommand.getPassword());
        if (! passwordN.equalsIgnoreCase(user.getPassword())) {
            throw new RuntimeException("密码验证不通过");
        }
        user.setPassword( MD5Utils.encrypt(newPwdCommand.getUserName(),newPwdCommand.getNewPassword()));
        return userMapper.updateByPrimaryKey(user);
    }
}
