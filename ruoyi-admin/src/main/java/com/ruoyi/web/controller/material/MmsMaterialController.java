package com.ruoyi.web.controller.material;

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
import com.ruoyi.material.domain.MmsMaterial;
import com.ruoyi.material.service.IMmsMaterialService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 材料管理Controller
 * 
 * @author linyuting
 * @date 2022-03-23
 */
@RestController
@RequestMapping("/material/material")
public class MmsMaterialController extends BaseController
{
    @Autowired
    private IMmsMaterialService mmsMaterialService;

    /**
     * 查询材料管理列表
     */
    @PreAuthorize("@ss.hasPermi('material:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsMaterial mmsMaterial)
    {
        startPage();
        List<MmsMaterial> list = mmsMaterialService.selectMmsMaterialList(mmsMaterial);
        return getDataTable(list);
    }

    /**
     * 导出材料管理列表
     */
    @PreAuthorize("@ss.hasPermi('material:material:export')")
    @Log(title = "材料管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsMaterial mmsMaterial)
    {
        List<MmsMaterial> list = mmsMaterialService.selectMmsMaterialList(mmsMaterial);
        ExcelUtil<MmsMaterial> util = new ExcelUtil<MmsMaterial>(MmsMaterial.class);
        util.exportExcel(response, list, "材料管理数据");
    }

    /**
     * 获取材料管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('material:material:query')")
    @GetMapping(value = "/{matId}")
    public AjaxResult getInfo(@PathVariable("matId") Long matId)
    {
        return AjaxResult.success(mmsMaterialService.selectMmsMaterialByMatId(matId));
    }

    /**
     * 新增材料管理
     */
    @PreAuthorize("@ss.hasPermi('material:material:add')")
    @Log(title = "材料管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsMaterial mmsMaterial)
    {
        return toAjax(mmsMaterialService.insertMmsMaterial(mmsMaterial));
    }

    /**
     * 修改材料管理
     */
    @PreAuthorize("@ss.hasPermi('material:material:edit')")
    @Log(title = "材料管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsMaterial mmsMaterial)
    {
        return toAjax(mmsMaterialService.updateMmsMaterial(mmsMaterial));
    }

    /**
     * 删除材料管理
     */
    @PreAuthorize("@ss.hasPermi('material:material:remove')")
    @Log(title = "材料管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{matIds}")
    public AjaxResult remove(@PathVariable Long[] matIds)
    {
        return toAjax(mmsMaterialService.deleteMmsMaterialByMatIds(matIds));
    }
}
