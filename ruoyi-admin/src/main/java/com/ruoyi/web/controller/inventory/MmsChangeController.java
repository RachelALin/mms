package com.ruoyi.web.controller.inventory;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
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
import com.ruoyi.inventory.domain.MmsChange;
import com.ruoyi.inventory.service.IMmsChangeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 材料调拨Controller
 * 
 * @author linyuting
 * @date 2022-04-26
 */
@RestController
@RequestMapping("/inventory/change")
public class MmsChangeController extends BaseController
{
    @Autowired
    private IMmsChangeService mmsChangeService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询材料调拨列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:change:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsChange mmsChange)
    {
        startPage();
        List<MmsChange> list = mmsChangeService.selectMmsChangeList(mmsChange);
        return getDataTable(list);
    }

    /**
     * 导出材料调拨列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:change:export')")
    @Log(title = "材料调拨", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsChange mmsChange)
    {
        List<MmsChange> list = mmsChangeService.selectMmsChangeList(mmsChange);
        ExcelUtil<MmsChange> util = new ExcelUtil<MmsChange>(MmsChange.class);
        util.exportExcel(response, list, "材料调拨数据");
    }

    /**
     * 获取材料调拨详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:change:query')")
    @GetMapping(value = "/{chaId}")
    public AjaxResult getInfo(@PathVariable("chaId") Long chaId)
    {
        return AjaxResult.success(mmsChangeService.selectMmsChangeByChaId(chaId));
    }

    /**
     * 新增材料调拨
     */
    @PreAuthorize("@ss.hasPermi('inventory:change:add')")
    @Log(title = "材料调拨", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsChange mmsChange, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsChange.setUserId(loginUser.getUserId());
        return toAjax(mmsChangeService.insertMmsChange(mmsChange));
    }

    /**
     * 修改材料调拨
     */
    @PreAuthorize("@ss.hasPermi('inventory:change:edit')")
    @Log(title = "材料调拨", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsChange mmsChange, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsChange.setUserId(loginUser.getUserId());
        return toAjax(mmsChangeService.updateMmsChange(mmsChange));
    }

    /**
     * 删除材料调拨
     */
    @PreAuthorize("@ss.hasPermi('inventory:change:remove')")
    @Log(title = "材料调拨", businessType = BusinessType.DELETE)
	@DeleteMapping("/{chaIds}")
    public AjaxResult remove(@PathVariable Long[] chaIds)
    {
        return toAjax(mmsChangeService.deleteMmsChangeByChaIds(chaIds));
    }
}
