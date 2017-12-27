package com.gmsj.system.controller;

import com.gmsj.common.controller.BaseController;
import com.gmsj.common.annotation.Log;
import com.gmsj.core.business.model.RestResp;
import com.gmsj.system.domain.RoleDO;
import com.gmsj.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sys/role")
@Controller
public class RoleController extends BaseController {
	String prefix = "system/role";
	@Autowired
	RoleService roleService;

	@RequiresPermissions("sys:role:role")
	@GetMapping()
	String role() {
		return prefix + "/role";
	}

	@RequiresPermissions("sys:role:role")
	@GetMapping("/list")
	@ResponseBody()
	List<RoleDO> list() {
		List<RoleDO> roles = roleService.list();
		return roles;
	}

	@Log("添加角色")
	@RequiresPermissions("sys:role:add")
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}

	@Log("编辑角色")
	@RequiresPermissions("sys:role:edit")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		RoleDO roleDO = roleService.get(id);
		model.addAttribute("role", roleDO);
		return prefix + "/edit";
	}

	@Log("保存角色")
	@RequiresPermissions("sys:role:add")
	@PostMapping("/save")
	@ResponseBody()
    RestResp save(RoleDO role) {

		if (roleService.save(role) > 0) {
			return RestResp.ok();
		} else {
			return RestResp.error(1, "保存失败");
		}
	}

	@Log("更新角色")
	@RequiresPermissions("sys:role:edit")
	@PostMapping("/update")
	@ResponseBody()
    RestResp update(RoleDO role) {

		if (roleService.update(role) > 0) {
			return RestResp.ok();
		} else {
			return RestResp.error(1, "保存失败");
		}
	}

	@Log("删除角色")
	@RequiresPermissions("sys:role:remove")
	@PostMapping("/remove")
	@ResponseBody()
    RestResp save(Long id) {

		if (roleService.remove(id) > 0) {
			return RestResp.ok();
		} else {
			return RestResp.error(1, "删除失败");
		}
	}
	
	@RequiresPermissions("sys:role:batchRemove")
	@Log("批量删除角色")
	@PostMapping("/batchRemove")
	@ResponseBody
    RestResp batchRemove(@RequestParam("ids[]") Long[] ids) {

		int r = roleService.batchremove(ids);
		if (r > 0) {
			return RestResp.ok();
		}
		return RestResp.error();
	}
	/**
	 * 通过部门id查询角色
	 * @param depetId
	 * @return
	 */
	@RequiresPermissions("sys:role:role")
	@RequestMapping(value = "/depetId",method = RequestMethod.POST)
	@ResponseBody()
	List<RoleDO> findByDepetId(Long depetId) {
		List<RoleDO> roles = roleService.findByDepetId(depetId);
		return roles;
	}

}
