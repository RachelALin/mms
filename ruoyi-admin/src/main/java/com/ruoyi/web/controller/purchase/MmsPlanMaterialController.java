package com.ruoyi.web.controller.purchase;

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
import com.ruoyi.purchase.domain.MmsPlanMaterial;
import com.ruoyi.purchase.service.IMmsPlanMaterialService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 材料与材料总计划Controller
 * 
 * @author linyuting
 * @date 2022-03-24
 */
@RestController
@RequestMapping("/purchase/plandetail")
public class MmsPlanMaterialController extends BaseController
{
    @Autowired
    private IMmsPlanMaterialService mmsPlanMaterialService;

    /**
     * 查询材料与材料总计划列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:plandetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsPlanMaterial mmsPlanMaterial)
    {
        startPage();
        List<MmsPlanMaterial> list = mmsPlanMaterialService.selectMmsPlanMaterialList(mmsPlanMaterial);
        return getDataTable(list);
    }

    /**
     * 导出材料与材料总计划列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:plandetail:export')")
    @Log(title = "材料与材料总计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsPlanMaterial mmsPlanMaterial)
    {
        List<MmsPlanMaterial> list = mmsPlanMaterialService.selectMmsPlanMaterialList(mmsPlanMaterial);
        ExcelUtil<MmsPlanMaterial> util = new ExcelUtil<MmsPlanMaterial>(MmsPlanMaterial.class);
        util.exportExcel(response, list, "材料与材料总计划数据");
    }

    /**
     * 获取材料与材料总计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:plandetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mmsPlanMaterialService.selectMmsPlanMaterialById(id));
    }

    /**
     * 新增材料与材料总计划
     */
    @PreAuthorize("@ss.hasPermi('purchase:plandetail:add')")
    @Log(title = "材料与材料总计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsPlanMaterial mmsPlanMaterial)
    {
        return toAjax(mmsPlanMaterialService.insertMmsPlanMaterial(mmsPlanMaterial));
    }

    /**
     * 修改材料与材料总计划
     */
    @PreAuthorize("@ss.hasPermi('purchase:plandetail:edit')")
    @Log(title = "材料与材料总计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsPlanMaterial mmsPlanMaterial)
    {
        return toAjax(mmsPlanMaterialService.updateMmsPlanMaterial(mmsPlanMaterial));
    }

    /**
     * 删除材料与材料总计划
     */
    @PreAuthorize("@ss.hasPermi('purchase:plandetail:remove')")
    @Log(title = "材料与材料总计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mmsPlanMaterialService.deleteMmsPlanMaterialByIds(ids));
    }
}
