package com.gmsj.user.mapper;

import com.gmsj.core.lib.MyBaseMapper;
import com.gmsj.user.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户相关操作DAO接口
 *
 * @author hongQiang tang
 * @version $Id: UserMapper.java, v 0.1 2017年6月30日 上午9:52:55 Administrator Exp $
 */
@Mapper
public interface UserMapper extends MyBaseMapper<User> {


    /**
     * 手机查找用户
     *
     * @param phone 手机号
     * @return 用户
     */
    User getUserByPhone(String phone);
}