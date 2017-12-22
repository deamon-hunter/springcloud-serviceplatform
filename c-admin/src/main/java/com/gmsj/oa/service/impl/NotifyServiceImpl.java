package com.gmsj.oa.service.impl;

import com.gmsj.common.service.DictService;
import com.gmsj.oa.domain.NotifyDO;
import com.gmsj.oa.mapper.NotifyRecordDao;
import com.gmsj.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gmsj.common.utils.DateUtils;
import com.gmsj.core.business.model.BootstrapTable;
import com.gmsj.oa.mapper.NotifyDao;
import com.gmsj.oa.domain.NotifyDTO;
import com.gmsj.oa.domain.NotifyRecordDO;
import com.gmsj.oa.service.NotifyService;

@Service
public class NotifyServiceImpl implements NotifyService {
	@Autowired
	private NotifyDao notifyDao;
	@Autowired
	private NotifyRecordDao recordDao;
	@Autowired
	private UserMapper userDao;
	@Autowired
	private DictService dictService;

	@Override
	public NotifyDO get(Long id) {
		NotifyDO rDO =  notifyDao.get(id);
		rDO.setType(dictService.getName("oa_notify_type", rDO.getType()) );
		return rDO;
	}

	@Override
	public List<NotifyDO> list(Map<String, Object> map) {
		List<NotifyDO> notifys = notifyDao.list(map);
		for (NotifyDO notifyDO : notifys) {
			notifyDO.setType(dictService.getName("oa_notify_type", notifyDO.getType()) );
		}
		return notifys;
	}

	@Override
	public int count(Map<String, Object> map) {
		return notifyDao.count(map);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int save(NotifyDO notify) {
		notify.setUpdateDate(new Date());
		int r = notifyDao.save(notify);
		// 保存到接受者列表中
		Long[] userIds = notify.getUserIds();
		Long notifyId = notify.getId();
		List<NotifyRecordDO> records = new ArrayList<>();
		for (Long userId : userIds) {
			NotifyRecordDO record = new NotifyRecordDO();
			record.setNotifyId(notifyId);
			record.setUserId(userId);
			record.setIsRead(0);
			records.add(record);
		}
		recordDao.batchSave(records);
		return r;

	}

	@Override
	public int update(NotifyDO notify) {
		return notifyDao.update(notify);
	}

	@Transactional
	@Override
	public int remove(Long id) {
		recordDao.removeByNotifbyId(id);
		return notifyDao.remove(id);
	}

	@Transactional
	@Override
	public int batchRemove(Long[] ids) {
		recordDao.batchRemoveByNotifbyId(ids);
		return notifyDao.batchRemove(ids);
	}

//	@Override
//	public Map<String, Object> message(Long userId) {
//		Map<String, Object> param = new HashMap<>(16);
//		Map<String, Object> map = new HashMap<>(16);
//		param.put("userId", userId);
//		param.put("isRead", 0);
//		int messageNum = recordDao.count(param);
//		Long[] notifyIds = recordDao.listNotifyIds(param);
//		List<NotifyDO> messages = notifyDao.listByIds(notifyIds);
//		map.put("messageNum", messageNum);
//		map.put("messages", messages);
//		return map;
//	}

	@Override
	public BootstrapTable selfList(Map<String, Object> map) {
		List<NotifyDTO> rows = notifyDao.listDTO(map);
		for (NotifyDTO notifyDTO : rows) {
			notifyDTO.setBefore(DateUtils.getTimeBefore(notifyDTO.getUpdateDate()));
			notifyDTO.setSender(userDao.get(notifyDTO.getCreateBy()).getName());
		}
		BootstrapTable page = new BootstrapTable(rows, notifyDao.countDTO(map));
		return page;
	}

}