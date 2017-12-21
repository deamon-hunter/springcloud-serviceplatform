package com.gmsj.common.mapper;

import com.gmsj.common.MyBaseMapper;
import com.gmsj.common.model.LogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * @author Orville
 * @@version 0.0.1
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface LogMapper extends MyBaseMapper<LogDO> {

	LogDO get(Long id);
	
	List<LogDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(LogDO log);
	
	int update(LogDO log);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
