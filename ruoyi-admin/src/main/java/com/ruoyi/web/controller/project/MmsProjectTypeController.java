package com.ruoyi.web.controller.project;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.project.domain.MmsProjectType;
import com.ruoyi.project.service.IMmsProjectTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目类型Controller
 * 
 * @author linyuting
 * @date 2022-03-16
 */
@RestController
@RequestMapping("/project/type")
public class MmsProjectTypeController extends BaseController
{
    @Autowired
    private IMmsProjectTypeService mmsProjectTypeService;

    /**
     * 查询项目类型列表
     */
    @PreAuthorize("@ss.hasPermi('project:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsProjectType mmsProjectType)
    {
        startPage();
        List<MmsProjectType> list = mmsProjectTypeService.selectMmsProjectTypeList(mmsProjectType);
        return getDataTable(list);
    }

    /**
     * 导出项目类型列表
     */
    @PreAuthorize("@ss.hasPermi('project:type:export')")
    @Log(title = "项目类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsProjectType mmsProjectType)
    {
        List<MmsProjectType> list = mmsProjectTypeService.selectMmsProjectTypeList(mmsProjectType);
        ExcelUtil<MmsProjectType> util = new ExcelUtil<MmsProjectType>(MmsProjectType.class);
        util.exportExcel(response, list, "项目类型数据");
    }

    /**
     * 获取项目类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mmsProjectTypeService.selectMmsProjectTypeById(id));
    }

    /**
     * 新增项目类型
     */
    @PreAuthorize("@ss.hasPermi('project:type:add')")
    @Log(title = "项目类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsProjectType mmsProjectType)
    {
        return toAjax(mmsProjectTypeService.insertMmsProjectType(mmsProjectType));
    }

    /**
     * 修改项目类型
     */
    @PreAuthorize("@ss.hasPermi('project:type:edit')")
    @Log(title = "项目类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsProjectType mmsProjectType)
    {
        return toAjax(mmsProjectTypeService.updateMmsProjectType(mmsProjectType));
    }

    /**
     * 删除项目类型
     */
    @PreAuthorize("@ss.hasPermi('project:type:remove')")
    @Log(title = "项目类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mmsProjectTypeService.deleteMmsProjectTypeByIds(ids));
    }
}
