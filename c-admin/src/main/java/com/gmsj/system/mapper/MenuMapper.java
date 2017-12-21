package com.gmsj.system.mapper;

import com.gmsj.common.MyBaseMapper;
import com.gmsj.system.domain.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * @author Orville
 * @@version 0.0.1
 * @date 2017-10-03 09:45:09
 */
@Mapper
public interface MenuMapper extends MyBaseMapper<MenuDO> {

	MenuDO get(Long menuId);

	List<MenuDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(MenuDO menu);

	int update(MenuDO menu);

	int remove(Long menuId);
	
	int batchRemove(Long[] menuIds);
	
	List<MenuDO> listMenuByUserId(Long id);
	
	List<String> listUserPerms(Long id);
}
