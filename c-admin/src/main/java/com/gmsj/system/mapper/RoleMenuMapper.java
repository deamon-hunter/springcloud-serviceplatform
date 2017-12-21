package com.gmsj.system.mapper;

import com.gmsj.common.MyBaseMapper;
import com.gmsj.system.domain.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * 角色与菜单对应关系
 * @author Orville
 * @@version 0.0.1
 * @date 2017-10-03 11:08:59
 */
@Mapper
public interface RoleMenuMapper extends MyBaseMapper<RoleMenuDO> {

	RoleMenuDO get(Long id);
	
	List<RoleMenuDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(RoleMenuDO roleMenu);
	
	int update(RoleMenuDO roleMenu);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	List<Long> listMenuIdByRoleId(Long roleId);
	
	int removeByRoleId(Long roleId);
	
	int batchSave(List<RoleMenuDO> list);
}
