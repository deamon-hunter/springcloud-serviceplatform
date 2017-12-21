package com.gmsj.user.service.impl;

import com.gmsj.core.business.exception.BizUserErrorCode;
import com.gmsj.core.business.exception.BusinessException;
import com.gmsj.core.util.BeanUtil;
import com.gmsj.core.util.DigestUtils;
import com.gmsj.core.util.id.IdUtil;
import com.gmsj.core.business.vo.user.AuthInfoVO;
import com.gmsj.core.business.vo.user.UserVO;
import com.gmsj.user.mapper.AuthInfoMapper;
import com.gmsj.user.mapper.AuthRealNameMapper;
import com.gmsj.user.model.User;
import com.gmsj.user.mapper.AuthScoreMapper;
import com.gmsj.user.mapper.UserMapper;
import com.gmsj.user.model.AuthInfo;
import com.gmsj.user.model.AuthScore;
import com.gmsj.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.Optional;

/**
 * 用户相关业务接口实现
 *
 * @author hongQiang tang
 * @version $Id: UserServiceImpl.java, v 0.1 2017年6月20日 下午4:07:38 Administrator Exp $
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    /**
     * 用户 DAO
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 认证信息 DAO
     */
    @Autowired
    private AuthInfoMapper authMapper;

    /**
     * 认证分信息 DAO
     */
    @Autowired
    private AuthScoreMapper authScoreMapper;

    /**
     * 实名认证 DAO
     */
    @Autowired
    private AuthRealNameMapper realNameMapper;


    @Override
    public User register(UserVO userVO) {


        User user = new User();
        user.setPhone(userVO.getPhone());
        user.setOpenId(userVO.getOpenId());
        // 查询数据库中是否包含相同的手机号
        Optional<User> optional = Optional.ofNullable(userMapper.selectOne(user));
        if (optional.isPresent()) {
            throw new BusinessException(BizUserErrorCode.USER_PHONE_ALREAD_EXISTS);
        }


        // 生成主键
        user.generateId();
        Long userId = user.getId();

        // 加密密码
        if (StringUtils.isNotBlank(userVO.getPassword())) {
            user.setPassword(DigestUtils.md5(userVO.getPassword()));
        }

		/*
         * 默认注册将连带执行以下操作：
		 * 1) 添加一条默认为空的认证信息，以后进行信息认证时只需要修改其某一字段值
		 * 2) 添加一条默认为空的认证分数据，以后认证后的分数只需要修改某一字段值即可
		 * 3) 注册用户
		 * 4) 保存登录/注册日志
		 */
        AuthInfo authInfo = new AuthInfo();
        authInfo.setId(IdUtil.generateId());
        authInfo.setUserId(userId);

        Assert.isTrue(authMapper.insertSelective(authInfo) > 0, "注册失败");
        AuthScore authScore = new AuthScore();
        authScore.setId(IdUtil.generateId());
        authScore.setUserId(userId);
        Assert.isTrue(authScoreMapper.insertSelective(authScore) > 0, "注册失败");
        Assert.isTrue(userMapper.insertSelective(user) > 0, "注册失败");
        return user;
    }

    @Override
    public UserVO getUserProfile(Long userId) {
        return null;
    }

    @Override
    public void updateUser(Long id, String nickName, String photoUrl, Boolean isModifyNickName) {

        int result = 0;
        if (isModifyNickName) {
            User user = new User();
            user.setId(id);
            user.setNickName(nickName);
            user.setUserName(nickName);
            result = userMapper.updateByPrimaryKeySelective(user);
        } else {

        }
        Assert.isTrue(result > 0, "修改失败");
    }

    @Override
    public void resetPassword(String phone, String password) {

        Example example = BeanUtil.createExample(User.class, "phone", phone);
        User user = new User();
        user.setPassword(DigestUtils.md5(password));
        final int row = userMapper.updateByExampleSelective(user, example);
        Assert.isTrue(row > 0, "重置密码失败");
    }

    @Override
    public UserVO getUserInfoByOpenId(String openId) {

        UserVO vo = new UserVO();

        User user = new User();
        user.setOpenId(openId);
        // 查询目标用户
        user = userMapper.selectOne(user);

        if (null == user) {
            throw new BusinessException(BizUserErrorCode.USER_NOT_REGISTER);
        }

        // 查询用户辅助信息
        AuthInfoVO authInfoVO = authMapper.selectAuthInfo(user.getId());
        vo.setAuthInfo(authInfoVO);


        return vo;
    }

    @Override
    public void setNewPassword(Long id, String password) {

        // 设置用户新密码
        User user = new User();
        user.setId(id);
        user.setPassword(DigestUtils.md5(password));
        final int row = userMapper.updateByPrimaryKeySelective(user);
        Assert.isTrue(row > 0, "设置密码失败");
    }
}
