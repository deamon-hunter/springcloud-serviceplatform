package com.gmsj.user.mapper;

import com.gmsj.core.lib.MyBaseMapper;
import com.gmsj.user.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends MyBaseMapper<User> {
}