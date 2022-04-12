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
import com.ruoyi.inventory.domain.MmsReceived;
import com.ruoyi.inventory.service.IMmsReceivedService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 材料入库Controller
 * 
 * @author linyuting
 * @date 2022-03-26
 */
@RestController
@RequestMapping("/inventory/received")
public class MmsReceivedController extends BaseController
{
    @Autowired
    private IMmsReceivedService mmsReceivedService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询材料入库列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:received:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsReceived mmsReceived)
    {
        startPage();
        List<MmsReceived> list = mmsReceivedService.selectMmsReceivedList(mmsReceived);
        return getDataTable(list);
    }

    /**
     * 导出材料入库列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:received:export')")
    @Log(title = "材料入库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsReceived mmsReceived)
    {
        List<MmsReceived> list = mmsReceivedService.selectMmsReceivedList(mmsReceived);
        ExcelUtil<MmsReceived> util = new ExcelUtil<MmsReceived>(MmsReceived.class);
        util.exportExcel(response, list, "材料入库数据");
    }

    /**
     * 获取材料入库详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:received:query')")
    @GetMapping(value = "/{inId}")
    public AjaxResult getInfo(@PathVariable("inId") Long inId)
    {
        return AjaxResult.success(mmsReceivedService.selectMmsReceivedByInId(inId));
    }

    /**
     * 新增材料入库
     */
    @PreAuthorize("@ss.hasPermi('inventory:received:add')")
    @Log(title = "材料入库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsReceived mmsReceived, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsReceived.setUserId(loginUser.getUserId());
        return toAjax(mmsReceivedService.insertMmsReceived(mmsReceived));
    }

    /**
     * 修改材料入库
     */
    @PreAuthorize("@ss.hasPermi('inventory:received:edit')")
    @Log(title = "材料入库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsReceived mmsReceived, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsReceived.setUserId(loginUser.getUserId());
        return toAjax(mmsReceivedService.updateMmsReceived(mmsReceived));
    }

    /**
     * 删除材料入库
     */
    @PreAuthorize("@ss.hasPermi('inventory:received:remove')")
    @Log(title = "材料入库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{inIds}")
    public AjaxResult remove(@PathVariable Long[] inIds)
    {
        return toAjax(mmsReceivedService.deleteMmsReceivedByInIds(inIds));
    }
}
