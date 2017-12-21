package com.gmsj.system.service;

import java.util.List;
import java.util.Set;

import com.gmsj.common.domain.Tree;
import com.gmsj.system.domain.MenuDO;
import org.springframework.stereotype.Service;

@Service
public interface MenuService {
	Tree<MenuDO> getSysMenuTree(Long id);

	List<Tree<MenuDO>> listMenuTree(Long id);

	Tree<MenuDO> getTree();

	Tree<MenuDO> getTree(Long id);

	List<MenuDO> list(Long userId);

	int remove(Long id);

	int save(MenuDO menu);

	int update(MenuDO menu);

	MenuDO get(Long id);

	Set<String> listPerms(Long userId);
}
