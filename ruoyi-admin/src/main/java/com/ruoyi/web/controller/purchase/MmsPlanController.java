package com.ruoyi.web.controller.purchase;

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
import com.ruoyi.purchase.domain.MmsPlan;
import com.ruoyi.purchase.service.IMmsPlanService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 材料总计划Controller
 * 
 * @author linyuting
 * @date 2022-03-23
 */
@RestController
@RequestMapping("/purchase/plan")
public class MmsPlanController extends BaseController
{
    @Autowired
    private IMmsPlanService mmsPlanService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询材料总计划列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsPlan mmsPlan)
    {
        startPage();
        List<MmsPlan> list = mmsPlanService.selectMmsPlanList(mmsPlan);
        return getDataTable(list);
    }

    /**
     * 导出材料总计划列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:plan:export')")
    @Log(title = "材料总计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsPlan mmsPlan)
    {
        List<MmsPlan> list = mmsPlanService.selectMmsPlanList(mmsPlan);
        ExcelUtil<MmsPlan> util = new ExcelUtil<MmsPlan>(MmsPlan.class);
        util.exportExcel(response, list, "材料总计划数据");
    }

    /**
     * 获取材料总计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:plan:query')")
    @GetMapping(value = "/{planId}")
    public AjaxResult getInfo(@PathVariable("planId") Long planId)
    {
        return AjaxResult.success(mmsPlanService.selectMmsPlanByPlanId(planId));
    }

    /**
     * 新增材料总计划
     */
    @PreAuthorize("@ss.hasPermi('purchase:plan:add')")
    @Log(title = "材料总计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsPlan mmsPlan, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsPlan.setUserId(loginUser.getUserId());
        return toAjax(mmsPlanService.insertMmsPlan(mmsPlan));
    }

    /**
     * 修改材料总计划
     */
    @PreAuthorize("@ss.hasPermi('purchase:plan:edit')")
    @Log(title = "材料总计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsPlan mmsPlan, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsPlan.setUserId(loginUser.getUserId());
        return toAjax(mmsPlanService.updateMmsPlan(mmsPlan));
    }

    /**
     * 删除材料总计划
     */
    @PreAuthorize("@ss.hasPermi('purchase:plan:remove')")
    @Log(title = "材料总计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{planIds}")
    public AjaxResult remove(@PathVariable Long[] planIds)
    {
        return toAjax(mmsPlanService.deleteMmsPlanByPlanIds(planIds));
    }

//    /**
//     * 查询计划详情
//     */
//    @PreAuthorize("@ss.hasPermi('purchase:plan:detail')")
//    @GetMapping("/{planIds}")
//    public AjaxResult detail(@PathVariable Long planIds)
//    {
//        return toAjax(mmsPlanService.selectMmsPlanDetailByPlanId(planIds));
//    }

}
