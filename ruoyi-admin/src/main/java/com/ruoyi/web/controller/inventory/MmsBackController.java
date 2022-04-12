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
import com.ruoyi.inventory.domain.MmsBack;
import com.ruoyi.inventory.service.IMmsBackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 材料退库Controller
 * 
 * @author linyuting
 * @date 2022-03-28
 */
@RestController
@RequestMapping("/inventory/back")
public class MmsBackController extends BaseController
{
    @Autowired
    private IMmsBackService mmsBackService;

    /**
     * 查询材料退库列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:back:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsBack mmsBack)
    {
        startPage();
        List<MmsBack> list = mmsBackService.selectMmsBackList(mmsBack);
        return getDataTable(list);
    }

    /**
     * 导出材料退库列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:back:export')")
    @Log(title = "材料退库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsBack mmsBack)
    {
        List<MmsBack> list = mmsBackService.selectMmsBackList(mmsBack);
        ExcelUtil<MmsBack> util = new ExcelUtil<MmsBack>(MmsBack.class);
        util.exportExcel(response, list, "材料退库数据");
    }

    /**
     * 获取材料退库详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:back:query')")
    @GetMapping(value = "/{backId}")
    public AjaxResult getInfo(@PathVariable("backId") Long backId)
    {
        return AjaxResult.success(mmsBackService.selectMmsBackByBackId(backId));
    }

    /**
     * 新增材料退库
     */
    @PreAuthorize("@ss.hasPermi('inventory:back:add')")
    @Log(title = "材料退库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsBack mmsBack)
    {
        return toAjax(mmsBackService.insertMmsBack(mmsBack));
    }

    /**
     * 修改材料退库
     */
    @PreAuthorize("@ss.hasPermi('inventory:back:edit')")
    @Log(title = "材料退库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsBack mmsBack)
    {
        return toAjax(mmsBackService.updateMmsBack(mmsBack));
    }

    /**
     * 删除材料退库
     */
    @PreAuthorize("@ss.hasPermi('inventory:back:remove')")
    @Log(title = "材料退库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{backIds}")
    public AjaxResult remove(@PathVariable Long[] backIds)
    {
        return toAjax(mmsBackService.deleteMmsBackByBackIds(backIds));
    }
}
