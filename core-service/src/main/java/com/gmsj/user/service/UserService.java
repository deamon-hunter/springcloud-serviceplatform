package com.gmsj.user.service;

import com.gmsj.user.model.User;
import com.gmsj.core.business.vo.user.UserVO;

/**
 * 用户相关业务接口定义
 *
 * @author hongQiang tang
 * @version $Id: UserService.java, v 0.1 2017年6月20日 下午4:06:33 Administrator Exp $
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param user 用户表单数据
     * @return
     */
    User register(UserVO userVO);

    /**
     * 重置密码操作
     *
     * @param phone    手机号码
     * @param password 新密码
     */
    void resetPassword(String phone, String password);

    /**
     * 设置新密码
     *
     * @param id
     * @param password
     */
    void setNewPassword(Long id, String password);

    /**
     * 修改用户头像或昵称
     *
     * @param id               用户ID
     * @param nickName         用户昵称
     * @param photoUrl         头像地址
     * @param isModifyNickName 内部用于判断是否是修改昵称操作
     */
    void updateUser(Long id, String nickName, String photoUrl, Boolean isModifyNickName);

    /**
     * 查询用户个人详情
     *
     * @param userId 用户ID
     * @return
     */
    UserVO getUserProfile(Long userId);

    UserVO getUserInfoByOpenId(String openId);
}
