package com.gmsj.system.service;

import java.util.List;

import com.gmsj.system.domain.RoleDO;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

	RoleDO get(Long id);

	List<RoleDO> list();

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Long id);

	List<RoleDO> list(Long userId);

	int batchremove(Long[] ids);
	/**
	 * 通过部门id查询角色信息
	 * @param depetId
	 * @return
	 */
	List<RoleDO> findByDepetId(Long depetId);
}
