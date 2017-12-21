package com.gmsj.common.controller;

import com.gmsj.common.service.DictService;
import com.gmsj.common.domain.DictDO;
import com.gmsj.core.business.model.BootstrapTable;
import com.gmsj.common.utils.Query;
import com.gmsj.core.business.model.RestResp;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */

@Controller
@RequestMapping("/common/sysDict")
public class DictController extends BaseController {
	@Autowired
	private DictService sysDictService;

	@GetMapping()
	@RequiresPermissions("common:sysDict:sysDict")
	String sysDict() {
		return "common/sysDict/sysDict";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:sysDict:sysDict")
	public BootstrapTable list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<DictDO> sysDictList = sysDictService.list(query);
		int total = sysDictService.count(query);
		BootstrapTable bootstrapTable = new BootstrapTable(sysDictList, total);
		return bootstrapTable;
	}

	@GetMapping("/add")
	@RequiresPermissions("common:sysDict:add")
	String add() {
		return "common/sysDict/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:sysDict:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		DictDO sysDict = sysDictService.get(id);
		model.addAttribute("sysDict", sysDict);
		return "common/sysDict/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:sysDict:add")
	public RestResp save(DictDO sysDict) {

		if (sysDictService.save(sysDict) > 0) {
			return RestResp.ok();
		}
		return RestResp.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:sysDict:edit")
	public RestResp update(DictDO sysDict) {

		sysDictService.update(sysDict);
		return RestResp.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("common:sysDict:remove")
	public RestResp remove(Long id) {

		if (sysDictService.remove(id) > 0) {
			return RestResp.ok();
		}
		return RestResp.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:sysDict:batchRemove")
	public RestResp remove(@RequestParam("ids[]") Long[] ids) {

		sysDictService.batchRemove(ids);
		return RestResp.ok();
	}

	@GetMapping("/type")
	@ResponseBody
	public List<DictDO> listType() {
		return sysDictService.listType();
	};

	// 类别已经指定增加
	@GetMapping("/add/{type}/{description}")
	@RequiresPermissions("common:sysDict:add")
	String addD(Model model, @PathVariable("type") String type, @PathVariable("description") String description) {
		model.addAttribute("type", type);
		model.addAttribute("description", description);
		return "common/sysDict/add";
	}

	@ResponseBody
	@GetMapping("/list/{type}")
	public List<DictDO> listByType(@PathVariable("type") String type) {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", type);
		List<DictDO> dictList = sysDictService.list(map);
		return dictList;
	}
}
