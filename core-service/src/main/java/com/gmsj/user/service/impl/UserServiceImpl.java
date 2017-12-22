package com.gmsj.user.service.impl;

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

/**
 * 用户相关业务接口实现
 *
 * @author hongQiang tang
 * @version $Id: UserServiceImpl.java, v 0.1 2017年6月20日 下午4:07:38 Administrator Exp $
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public LoginTokenDataVO authenticate(String userName, String password) {

        Example example = new Example( User.class );
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo( "userName", userName );
        List<User> users =userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            throw new RuntimeException("未找到登陆用户");
        }
        User user =users.get(0);
        String passwordN = MD5Utils.encrypt(userName,password);
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
    public void modifyPwd(String userName, String password, String newPassword) {
        Example example = new Example( User.class );
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo( "userName", userName );
        List<User> users =userMapper.selectByExample(example);

        User user = users.get(0);
        if (CollectionUtils.isEmpty(users)) {
            throw new RuntimeException("未找到登陆用户");
        }
        String passwordN = MD5Utils.encrypt(userName,password);
        if (! passwordN.equalsIgnoreCase(user.getPassword())) {
            throw new RuntimeException("密码验证不通过");
        }
        user.setPassword( MD5Utils.encrypt(userName,newPassword));
        userMapper.updateByPrimaryKey(user);

    }
}
