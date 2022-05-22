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
import com.ruoyi.inventory.domain.MmsCheck;
import com.ruoyi.inventory.service.IMmsCheckService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 材料盘点Controller
 * 
 * @author linyuting
 * @date 2022-04-26
 */
@RestController
@RequestMapping("/inventory/check")
public class MmsCheckController extends BaseController
{
    @Autowired
    private IMmsCheckService mmsCheckService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询材料盘点列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:check:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsCheck mmsCheck)
    {
        startPage();
        List<MmsCheck> list = mmsCheckService.selectMmsCheckList(mmsCheck);
        return getDataTable(list);
    }

    /**
     * 导出材料盘点列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:check:export')")
    @Log(title = "材料盘点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsCheck mmsCheck)
    {
        List<MmsCheck> list = mmsCheckService.selectMmsCheckList(mmsCheck);
        ExcelUtil<MmsCheck> util = new ExcelUtil<MmsCheck>(MmsCheck.class);
        util.exportExcel(response, list, "材料盘点数据");
    }

    /**
     * 获取材料盘点详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:check:query')")
    @GetMapping(value = "/{cheId}")
    public AjaxResult getInfo(@PathVariable("cheId") Long cheId)
    {
        return AjaxResult.success(mmsCheckService.selectMmsCheckByCheId(cheId));
    }

    /**
     * 新增材料盘点
     */
    @PreAuthorize("@ss.hasPermi('inventory:check:add')")
    @Log(title = "材料盘点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsCheck mmsCheck, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsCheck.setUserId(loginUser.getUserId());
        return toAjax(mmsCheckService.insertMmsCheck(mmsCheck));
    }

    /**
     * 修改材料盘点
     */
    @PreAuthorize("@ss.hasPermi('inventory:check:edit')")
    @Log(title = "材料盘点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsCheck mmsCheck, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsCheck.setUserId(loginUser.getUserId());
        return toAjax(mmsCheckService.updateMmsCheck(mmsCheck));
    }

    /**
     * 删除材料盘点
     */
    @PreAuthorize("@ss.hasPermi('inventory:check:remove')")
    @Log(title = "材料盘点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cheIds}")
    public AjaxResult remove(@PathVariable Long[] cheIds)
    {
        return toAjax(mmsCheckService.deleteMmsCheckByCheIds(cheIds));
    }
}
