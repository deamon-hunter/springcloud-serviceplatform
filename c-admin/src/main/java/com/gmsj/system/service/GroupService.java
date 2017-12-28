package com.gmsj.system.service;

import com.gmsj.system.domain.GroupDO;

import java.util.List;
import java.util.Map;

/**
 * 机构归属的系统组（平台煤企/服务商/crm等）
 * 
 * @author niechen
 * @email 1992lcg@163.com
 * @date 2017-12-27 17:05:41
 */
public interface GroupService {
	
	GroupDO get(Long id);
	
	List<GroupDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GroupDO group);
	
	int update(GroupDO group);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
