package com.ruoyi.web.controller.project;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.project.domain.MmsProject;
import com.ruoyi.project.service.IMmsProjectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目立项Controller
 * 
 * @author linyuting
 * @date 2022-03-16
 */
@RestController
@RequestMapping("/project/project")
public class MmsProjectController extends BaseController
{
    @Autowired
    private IMmsProjectService mmsProjectService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询项目立项列表
     */
    @PreAuthorize("@ss.hasPermi('project:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsProject mmsProject)
    {
        startPage();
        List<MmsProject> list = mmsProjectService.selectMmsProjectList(mmsProject);
        return getDataTable(list);
    }

    /**
     * 导出项目立项列表
     */
    @PreAuthorize("@ss.hasPermi('project:project:export')")
    @Log(title = "项目立项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsProject mmsProject)
    {
        List<MmsProject> list = mmsProjectService.selectMmsProjectList(mmsProject);
        ExcelUtil<MmsProject> util = new ExcelUtil<MmsProject>(MmsProject.class);
        util.exportExcel(response, list, "项目立项数据");
    }

    /**
     * 获取项目立项详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:project:query')")
    @GetMapping(value = "/{proId}")
    public AjaxResult getInfo(@PathVariable("proId") Long proId)
    {
        return AjaxResult.success(mmsProjectService.selectMmsProjectByProId(proId));
    }

    /**
     * 新增项目立项
     */
    @PreAuthorize("@ss.hasPermi('project:project:add')")
    @Log(title = "项目立项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsProject mmsProject, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsProject.setUserId(loginUser.getUserId());
        return toAjax(mmsProjectService.insertMmsProject(mmsProject));
    }

    /**
     * 修改项目立项
     */
    @PreAuthorize("@ss.hasPermi('project:project:edit')")
    @Log(title = "项目立项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsProject mmsProject, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsProject.setUserId(loginUser.getUserId());
        return toAjax(mmsProjectService.updateMmsProject(mmsProject));
    }

    /**
     * 删除项目立项
     */
    @PreAuthorize("@ss.hasPermi('project:project:remove')")
    @Log(title = "项目立项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{proIds}")
    public AjaxResult remove(@PathVariable Long[] proIds)
    {
        return toAjax(mmsProjectService.deleteMmsProjectByProIds(proIds));
    }



    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('project:project:userslist')")
    @GetMapping("/userslist")
    public TableDataInfo userslist(SysUser user)
    {
        startPage();
        List<SysUser> userslist = userService.selectUserList(user);
        return getDataTable(userslist);
    }

}
