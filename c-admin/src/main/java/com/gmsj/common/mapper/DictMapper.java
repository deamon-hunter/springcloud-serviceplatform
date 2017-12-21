package com.gmsj.common.mapper;

import com.gmsj.common.MyBaseMapper;
import com.gmsj.common.model.DictDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author Orville
 * @@version 0.0.1
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface DictMapper extends MyBaseMapper<DictDO> {

	DictDO get(Long id);

	List<DictDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(DictDO dict);

	int update(DictDO dict);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<DictDO> listType();
}
