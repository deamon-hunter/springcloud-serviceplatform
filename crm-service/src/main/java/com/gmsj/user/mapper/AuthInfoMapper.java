package com.gmsj.user.mapper;

import com.gmsj.core.business.vo.user.AuthInfoVO;
import com.gmsj.core.lib.MyBaseMapper;
import com.gmsj.user.model.AuthInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthInfoMapper extends MyBaseMapper<AuthInfo> {
    /**
     * 查询用户辅助信息
     *
     * @param userId 用户ID
     * @return
     */
    AuthInfoVO selectAuthInfo(Long userId);
}