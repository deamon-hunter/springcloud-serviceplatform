package com.gmsj.common.controller;

import com.gmsj.system.domain.UserDO;
import org.springframework.stereotype.Controller;
import com.gmsj.common.utils.ShiroUtils;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}