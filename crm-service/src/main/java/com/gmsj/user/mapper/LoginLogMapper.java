package com.gmsj.user.mapper;

import com.gmsj.core.lib.MyBaseMapper;
import com.gmsj.user.model.LoginLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper extends MyBaseMapper<LoginLog> {
}