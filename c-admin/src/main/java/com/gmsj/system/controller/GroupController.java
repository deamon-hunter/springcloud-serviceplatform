package com.gmsj.system.controller;

import com.gmsj.core.business.model.RestResp;
import com.gmsj.system.domain.GroupDO;
import com.gmsj.system.service.GroupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构归属的系统组（平台煤企/服务商/crm等）
 * 
 * @author niechen
 * @email 1992lcg@163.com
 * @date 2017-12-27 17:05:41
 */
 
@Controller
@RequestMapping("/system/group")
public class GroupController {
	@Autowired
	private GroupService groupService;
	
	@GetMapping()
	@RequiresPermissions("system:group:group")
	String Group(){
	    return "system/group/group";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:group:group")
	public List<GroupDO> list(){
		//查询列表数据
		Map<String, Object> query = new HashMap<>(16);
		List<GroupDO> list=groupService.list(query);
		return list;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:group:add")
	String add(){
	    return "system/group/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:group:edit")
	String edit(@PathVariable("id") Long id,Model model){
		GroupDO group = groupService.get(id);
		model.addAttribute("group", group);
	    return "system/group/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:group:add")
	public RestResp save(GroupDO group){
		if(groupService.save(group)>0){
			return RestResp.ok();
		}
		return RestResp.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:group:edit")
	public RestResp update( GroupDO group){
		groupService.update(group);
		return RestResp.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:group:remove")
	public RestResp remove( Long id){
		if(groupService.remove(id)>0){
		return RestResp.ok();
		}
		return RestResp.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:group:batchRemove")
	public RestResp remove(@RequestParam("ids[]") Long[] ids){
		groupService.batchRemove(ids);
		return RestResp.ok();
	}
	
}
