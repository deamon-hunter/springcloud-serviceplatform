package com.gmsj.common.controller;

import com.google.common.collect.ImmutableMap;
import com.gmsj.common.domain.TaskDO;
import com.gmsj.common.service.JobService;
import com.gmsj.core.business.model.BootstrapTable;
import com.gmsj.common.utils.Query;
import com.gmsj.core.business.model.RestResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-26 20:53:48
 */
@Controller
@RequestMapping("/common/job")
public class JobController extends BaseController{
	@Autowired
	private JobService taskScheduleJobService;

	@GetMapping()
	String taskScheduleJob() {
		return "common/job/job";
	}

	@ResponseBody
	@GetMapping("/list")
	public BootstrapTable list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<TaskDO> taskScheduleJobList = taskScheduleJobService.list(query);
		int total = taskScheduleJobService.count(query);
		BootstrapTable bootstrapTable = new BootstrapTable(taskScheduleJobList, total);
		return bootstrapTable;
	}

	@GetMapping("/add")
	String add() {
		return "common/job/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		TaskDO job = taskScheduleJobService.get(id);
		model.addAttribute("job", job);
		return "common/job/edit";
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public RestResp info(@PathVariable("id") Long id) {
		TaskDO taskScheduleJob = taskScheduleJobService.get(id);
		return RestResp.ok(ImmutableMap.of("taskScheduleJob", taskScheduleJob));
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public RestResp save(TaskDO taskScheduleJob) {

		if (taskScheduleJobService.save(taskScheduleJob) > 0) {
			return RestResp.ok();
		}
		return RestResp.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@PostMapping("/update")
	public RestResp update(TaskDO taskScheduleJob) {

		taskScheduleJobService.update(taskScheduleJob);
		return RestResp.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	public RestResp remove(Long id) {

		if (taskScheduleJobService.remove(id) > 0) {
			return RestResp.ok();
		}
		return RestResp.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	public RestResp remove(@RequestParam("ids[]") Long[] ids) {

		taskScheduleJobService.batchRemove(ids);

		return RestResp.ok();
	}

	@PostMapping(value = "/changeJobStatus")
	@ResponseBody
	public RestResp changeJobStatus(Long id, String cmd ) {

		String label = "停止";
		if ("start".equals(cmd)) {
			label = "启动";
		} else {
			label = "停止";
		}
		try {
			taskScheduleJobService.changeStatus(id, cmd);
			return RestResp.ok("任务" + label + "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RestResp.ok("任务" + label + "失败");
	}

}
