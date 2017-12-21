package com.gmsj.system.service;

import java.util.Collection;
import java.util.List;

import com.gmsj.system.domain.UserOnline;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {
	List<UserOnline> list();

	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);
}
