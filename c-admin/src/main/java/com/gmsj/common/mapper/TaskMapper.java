package com.gmsj.common.mapper;

import com.gmsj.common.MyBaseMapper;
import com.gmsj.common.model.TaskDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Orville
 * @@version 0.0.1
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface TaskMapper extends MyBaseMapper<TaskDO> {

	TaskDO get(Long id);
	
	List<TaskDO> listResult(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(TaskDO task);
	
	int update(TaskDO task);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
