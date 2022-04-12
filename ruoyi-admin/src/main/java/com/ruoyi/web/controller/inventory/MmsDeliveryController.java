package com.ruoyi.web.controller.inventory;

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
import com.ruoyi.inventory.domain.MmsDelivery;
import com.ruoyi.inventory.service.IMmsDeliveryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 材料出库Controller
 * 
 * @author linyuting
 * @date 2022-03-28
 */
@RestController
@RequestMapping("/inventory/delivery")
public class MmsDeliveryController extends BaseController
{
    @Autowired
    private IMmsDeliveryService mmsDeliveryService;

    /**
     * 查询材料出库列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:delivery:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsDelivery mmsDelivery)
    {
        startPage();
        List<MmsDelivery> list = mmsDeliveryService.selectMmsDeliveryList(mmsDelivery);
        return getDataTable(list);
    }

    /**
     * 导出材料出库列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:delivery:export')")
    @Log(title = "材料出库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsDelivery mmsDelivery)
    {
        List<MmsDelivery> list = mmsDeliveryService.selectMmsDeliveryList(mmsDelivery);
        ExcelUtil<MmsDelivery> util = new ExcelUtil<MmsDelivery>(MmsDelivery.class);
        util.exportExcel(response, list, "材料出库数据");
    }

    /**
     * 获取材料出库详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:delivery:query')")
    @GetMapping(value = "/{outId}")
    public AjaxResult getInfo(@PathVariable("outId") Long outId)
    {
        return AjaxResult.success(mmsDeliveryService.selectMmsDeliveryByOutId(outId));
    }

    /**
     * 新增材料出库
     */
    @PreAuthorize("@ss.hasPermi('inventory:delivery:add')")
    @Log(title = "材料出库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsDelivery mmsDelivery)
    {
        return toAjax(mmsDeliveryService.insertMmsDelivery(mmsDelivery));
    }

    /**
     * 修改材料出库
     */
    @PreAuthorize("@ss.hasPermi('inventory:delivery:edit')")
    @Log(title = "材料出库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsDelivery mmsDelivery)
    {
        return toAjax(mmsDeliveryService.updateMmsDelivery(mmsDelivery));
    }

    /**
     * 删除材料出库
     */
    @PreAuthorize("@ss.hasPermi('inventory:delivery:remove')")
    @Log(title = "材料出库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{outIds}")
    public AjaxResult remove(@PathVariable Long[] outIds)
    {
        return toAjax(mmsDeliveryService.deleteMmsDeliveryByOutIds(outIds));
    }
}
