package com.ruoyi.web.controller.supplier;

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
import com.ruoyi.supplier.domain.MmsSupplierType;
import com.ruoyi.supplier.service.IMmsSupplierTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商类型管理Controller
 * 
 * @author linyuting
 * @date 2022-03-18
 */
@RestController
@RequestMapping("/supplier/type")
public class MmsSupplierTypeController extends BaseController
{
    @Autowired
    private IMmsSupplierTypeService mmsSupplierTypeService;

    /**
     * 查询供应商类型管理列表
     */
    @PreAuthorize("@ss.hasPermi('supplier:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsSupplierType mmsSupplierType)
    {
        startPage();
        List<MmsSupplierType> list = mmsSupplierTypeService.selectMmsSupplierTypeList(mmsSupplierType);
        return getDataTable(list);
    }

    /**
     * 导出供应商类型管理列表
     */
    @PreAuthorize("@ss.hasPermi('supplier:type:export')")
    @Log(title = "供应商类型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsSupplierType mmsSupplierType)
    {
        List<MmsSupplierType> list = mmsSupplierTypeService.selectMmsSupplierTypeList(mmsSupplierType);
        ExcelUtil<MmsSupplierType> util = new ExcelUtil<MmsSupplierType>(MmsSupplierType.class);
        util.exportExcel(response, list, "供应商类型管理数据");
    }

    /**
     * 获取供应商类型管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('supplier:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mmsSupplierTypeService.selectMmsSupplierTypeById(id));
    }

    /**
     * 新增供应商类型管理
     */
    @PreAuthorize("@ss.hasPermi('supplier:type:add')")
    @Log(title = "供应商类型管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsSupplierType mmsSupplierType)
    {
        return toAjax(mmsSupplierTypeService.insertMmsSupplierType(mmsSupplierType));
    }

    /**
     * 修改供应商类型管理
     */
    @PreAuthorize("@ss.hasPermi('supplier:type:edit')")
    @Log(title = "供应商类型管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsSupplierType mmsSupplierType)
    {
        return toAjax(mmsSupplierTypeService.updateMmsSupplierType(mmsSupplierType));
    }

    /**
     * 删除供应商类型管理
     */
    @PreAuthorize("@ss.hasPermi('supplier:type:remove')")
    @Log(title = "供应商类型管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mmsSupplierTypeService.deleteMmsSupplierTypeByIds(ids));
    }
}
