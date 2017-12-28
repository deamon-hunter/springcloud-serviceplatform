package com.gmsj.system.service.impl;

import com.gmsj.system.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


import com.gmsj.system.domain.GroupDO;
import com.gmsj.system.service.GroupService;



@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupMapper groupDao;
	
	@Override
	public GroupDO get(Long id){
		return groupDao.get(id);
	}
	
	@Override
	public List<GroupDO> list(Map<String, Object> map){
		return groupDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return groupDao.count(map);
	}
	
	@Override
	public int save(GroupDO group){
		return groupDao.save(group);
	}
	
	@Override
	public int update(GroupDO group){
		return groupDao.update(group);
	}
	
	@Override
	public int remove(Long id){
		return groupDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return groupDao.batchRemove(ids);
	}
	
}
