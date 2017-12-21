package com.gmsj.system.mapper;

import com.gmsj.common.MyBaseMapper;
import com.gmsj.system.domain.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * @author Orville
 * @@version 0.0.1
 * @date 2017-10-02 20:24:47
 */
@Mapper
public interface RoleMapper extends MyBaseMapper<RoleDO> {

	RoleDO get(Long roleId);
	
	List<RoleDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(RoleDO role);
	
	int update(RoleDO role);
	
	int remove(Long roleId);
	
	int batchRemove(Long[] roleIds);
}
