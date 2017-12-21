package com.gmsj.common.mapper;

import com.gmsj.common.MyBaseMapper;
import com.gmsj.common.model.FileDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * @author Orville
 * @@version 0.0.1
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface FileMapper extends MyBaseMapper<FileDO> {

	FileDO get(Long id);
	
	List<FileDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(FileDO file);
	
	int update(FileDO file);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
