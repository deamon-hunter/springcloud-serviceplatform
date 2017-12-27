package com.gmsj.user.mapper;

import com.gmsj.core.lib.MyBaseMapper;
import com.gmsj.user.model.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends MyBaseMapper<Log> {
}