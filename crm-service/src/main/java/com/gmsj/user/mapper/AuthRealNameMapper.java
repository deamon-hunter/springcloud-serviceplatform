package com.gmsj.user.mapper;

import com.gmsj.core.lib.MyBaseMapper;
import com.gmsj.user.model.AuthRealName;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 实名信息认证DAO接口
 *
 * @author hongQiang tang
 * @version $Id: AuthRealNameMapper.java, v 0.1 2017年6月30日 上午9:54:17 Administrator Exp $
 */
@Mapper
public interface AuthRealNameMapper extends MyBaseMapper<AuthRealName> {

    /**
     * Map {
     * name : "张三",
     * idCardNumber : "00000000"
     * }
     *
     * @param id 用户ID
     * @return
     */
    Map<String, Object> selectRealNameInfo(Long id);
}