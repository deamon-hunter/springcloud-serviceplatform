package com.gmsj.system.mapper;

import com.gmsj.common.MyBaseMapper;
import com.gmsj.system.domain.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 * 
 * @author Orville
 * @@version 0.0.1
 * @date 2017-10-03 11:08:59
 */
@Mapper
public interface UserRoleMapper extends MyBaseMapper<UserRoleDO> {

	UserRoleDO get(Long id);

	List<UserRoleDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserRoleDO userRole);

	int update(UserRoleDO userRole);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<Long> listRoleId(Long userId);

	int removeByUserId(Long userId);

	int batchSave(List<UserRoleDO> list);

	int batchRemoveByUserId(Long[] ids);
}
