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
import com.ruoyi.purchase.domain.MmsNeed;
import com.ruoyi.purchase.service.IMmsNeedService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 材料需用计划Controller
 * 
 * @author linyuting
 * @date 2022-03-24
 */
@RestController
@RequestMapping("/purchase/need")
public class MmsNeedController extends BaseController
{
    @Autowired
    private IMmsNeedService mmsNeedService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询材料需用计划列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:need:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsNeed mmsNeed)
    {
        startPage();
        List<MmsNeed> list = mmsNeedService.selectMmsNeedList(mmsNeed);
        return getDataTable(list);
    }

    /**
     * 导出材料需用计划列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:need:export')")
    @Log(title = "材料需用计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsNeed mmsNeed)
    {
        List<MmsNeed> list = mmsNeedService.selectMmsNeedList(mmsNeed);
        ExcelUtil<MmsNeed> util = new ExcelUtil<MmsNeed>(MmsNeed.class);
        util.exportExcel(response, list, "材料需用计划数据");
    }

    /**
     * 获取材料需用计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:need:query')")
    @GetMapping(value = "/{needId}")
    public AjaxResult getInfo(@PathVariable("needId") Long needId)
    {
        return AjaxResult.success(mmsNeedService.selectMmsNeedByNeedId(needId));
    }

    /**
     * 新增材料需用计划
     */
    @PreAuthorize("@ss.hasPermi('purchase:need:add')")
    @Log(title = "材料需用计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsNeed mmsNeed, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsNeed.setUserId(loginUser.getUserId());
        return toAjax(mmsNeedService.insertMmsNeed(mmsNeed));
    }

    /**
     * 修改材料需用计划
     */
    @PreAuthorize("@ss.hasPermi('purchase:need:edit')")
    @Log(title = "材料需用计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsNeed mmsNeed, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsNeed.setUserId(loginUser.getUserId());
        return toAjax(mmsNeedService.updateMmsNeed(mmsNeed));
    }

    /**
     * 删除材料需用计划
     */
    @PreAuthorize("@ss.hasPermi('purchase:need:remove')")
    @Log(title = "材料需用计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{needIds}")
    public AjaxResult remove(@PathVariable Long[] needIds)
    {
        return toAjax(mmsNeedService.deleteMmsNeedByNeedIds(needIds));
    }
}
