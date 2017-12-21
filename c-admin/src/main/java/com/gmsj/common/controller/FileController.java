package com.gmsj.common.controller;

import com.gmsj.common.domain.FileDO;
import com.gmsj.common.service.FileService;
import com.gmsj.common.utils.FileType;
import com.gmsj.common.utils.FileUtil;
import com.gmsj.common.utils.Query;
import com.google.common.collect.ImmutableMap;
import com.gmsj.common.utils.*;
import com.gmsj.core.business.model.BootstrapTable;
import com.gmsj.core.business.model.RestResp;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
@Controller
@RequestMapping("/common/sysFile")
public class FileController extends BaseController {

    @Autowired
    private FileService sysFileService;

    @Autowired
    private com.gmsj.common.config.XAdminConfig XAdminConfig;

    @GetMapping()
    @RequiresPermissions("common:sysFile:sysFile")
    String sysFile(Model model) {
        Map<String, Object> params = new HashMap<>(16);
        return "common/file/file";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("common:sysFile:sysFile")
    public BootstrapTable list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<FileDO> sysFileList = sysFileService.list(query);
        int total = sysFileService.count(query);
        BootstrapTable bootstrapTable = new BootstrapTable(sysFileList, total);
        return bootstrapTable;
    }

    @GetMapping("/add")
        // @RequiresPermissions("common:bComments")
    String add() {
        return "common/sysFile/add";
    }

    @GetMapping("/edit")
        // @RequiresPermissions("common:bComments")
    String edit(Long id, Model model) {
        FileDO sysFile = sysFileService.get(id);
        model.addAttribute("sysFile", sysFile);
        return "common/sysFile/edit";
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("common:info")
    public FileDO info(@PathVariable("id") Long id) {
        FileDO sysFile = sysFileService.get(id);
        return sysFile;
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("common:save")
    public RestResp save(FileDO sysFile) {
        if (sysFileService.save(sysFile) > 0) {
            return RestResp.ok();
        }
        return RestResp.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("common:update")
    public RestResp update(@RequestBody FileDO sysFile) {
        sysFileService.update(sysFile);

        return RestResp.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    // @RequiresPermissions("common:remove")
    public RestResp remove(Long id, HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return RestResp.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String fileName = XAdminConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");
        if (sysFileService.remove(id) > 0) {
            boolean b = FileUtil.deleteFile(fileName);
            if (!b) {
                return RestResp.error(1, "数据库记录删除成功，文件删除失败");
            }
            return RestResp.ok();
        } else {
            return RestResp.error();
        }
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("common:remove")
    public RestResp remove(@RequestParam("ids[]") Long[] ids) {
        if ("test".equals(getUsername())) {
            return RestResp.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        sysFileService.batchRemove(ids);
        return RestResp.ok();
    }

    @ResponseBody
    @PostMapping("/upload")
    RestResp upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return RestResp.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        try {
            FileUtil.uploadFile(file.getBytes(), XAdminConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            return RestResp.error();
        }

        if (sysFileService.save(sysFile) > 0) {
            return RestResp.ok(ImmutableMap.of("fileName", sysFile.getUrl()));
        }
        return RestResp.error();
    }
}
