package com.gmsj.common.controller;

import java.util.Map;

import com.gmsj.common.domain.PageDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gmsj.common.domain.LogDO;
import com.gmsj.common.service.LogService;
import com.gmsj.common.utils.Query;
import com.gmsj.core.business.model.RestResp;

@RequestMapping("/common/log")
@Controller
public class LogController {
	@Autowired
	LogService logService;
	String prefix = "common/log";

	@GetMapping()
	String log() {
		return prefix + "/log";
	}

	@ResponseBody
	@GetMapping("/list")
    PageDO<LogDO> list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageDO<LogDO> page = logService.queryList(query);
		return page;
	}
	
	@ResponseBody
	@PostMapping("/remove")
    RestResp remove(Long id) {
		if (logService.remove(id)>0) {
			return RestResp.ok();
		}
		return RestResp.error();
	}

	@ResponseBody
	@PostMapping("/batchRemove")
    RestResp batchRemove(@RequestParam("ids[]") Long[] ids) {
		int r = logService.batchRemove(ids);
		if (r > 0) {
			return RestResp.ok();
		}
		return RestResp.error();
	}
}
