package com.gmsj.common.service;

import com.gmsj.common.domain.PageDO;
import org.springframework.stereotype.Service;

import com.gmsj.common.domain.LogDO;
import com.gmsj.common.utils.Query;
@Service
public interface LogService {
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
