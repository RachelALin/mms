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
import com.ruoyi.supplier.domain.MmsSupplier;
import com.ruoyi.supplier.service.IMmsSupplierService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商管理Controller
 * 
 * @author linyuting
 * @date 2022-03-18
 */
@RestController
@RequestMapping("/supplier/supplier")
public class MmsSupplierController extends BaseController
{
    @Autowired
    private IMmsSupplierService mmsSupplierService;

    /**
     * 查询供应商管理列表
     */
    @PreAuthorize("@ss.hasPermi('supplier:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsSupplier mmsSupplier)
    {
        startPage();
        List<MmsSupplier> list = mmsSupplierService.selectMmsSupplierList(mmsSupplier);
        return getDataTable(list);
    }

    /**
     * 导出供应商管理列表
     */
    @PreAuthorize("@ss.hasPermi('supplier:supplier:export')")
    @Log(title = "供应商管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsSupplier mmsSupplier)
    {
        List<MmsSupplier> list = mmsSupplierService.selectMmsSupplierList(mmsSupplier);
        ExcelUtil<MmsSupplier> util = new ExcelUtil<MmsSupplier>(MmsSupplier.class);
        util.exportExcel(response, list, "供应商管理数据");
    }

    /**
     * 获取供应商管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('supplier:supplier:query')")
    @GetMapping(value = "/{supplierId}")
    public AjaxResult getInfo(@PathVariable("supplierId") Long supplierId)
    {
        return AjaxResult.success(mmsSupplierService.selectMmsSupplierBySupplierId(supplierId));
    }

    /**
     * 新增供应商管理
     */
    @PreAuthorize("@ss.hasPermi('supplier:supplier:add')")
    @Log(title = "供应商管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsSupplier mmsSupplier)
    {
        return toAjax(mmsSupplierService.insertMmsSupplier(mmsSupplier));
    }

    /**
     * 修改供应商管理
     */
    @PreAuthorize("@ss.hasPermi('supplier:supplier:edit')")
    @Log(title = "供应商管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsSupplier mmsSupplier)
    {
        return toAjax(mmsSupplierService.updateMmsSupplier(mmsSupplier));
    }

    /**
     * 删除供应商管理
     */
    @PreAuthorize("@ss.hasPermi('supplier:supplier:remove')")
    @Log(title = "供应商管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{supplierIds}")
    public AjaxResult remove(@PathVariable Long[] supplierIds)
    {
        return toAjax(mmsSupplierService.deleteMmsSupplierBySupplierIds(supplierIds));
    }
}
