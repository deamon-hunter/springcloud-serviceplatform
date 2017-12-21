package com.gmsj.system.mapper;

import com.gmsj.common.MyBaseMapper;
import com.gmsj.system.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Orville
 * @@version 0.0.1
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserMapper extends MyBaseMapper<UserDO> {

	UserDO get(Long userId);
	
	List<UserDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();
}
