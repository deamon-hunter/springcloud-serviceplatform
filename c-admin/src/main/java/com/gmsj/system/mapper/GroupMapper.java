package com.gmsj.system.mapper;

import com.gmsj.common.MyBaseMapper;
import com.gmsj.system.domain.GroupDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 机构归属的系统组（平台煤企/服务商/crm等）
 * @author niechen
 * @date 2017-12-27 17:05:41
 */
@Mapper
public interface GroupMapper extends MyBaseMapper<GroupDO> {

	GroupDO get(Long id);
	
	List<GroupDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GroupDO group);
	
	int update(GroupDO group);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
