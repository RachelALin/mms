package com.ruoyi.web.controller.storage;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.storage.domain.MmsStore;
import com.ruoyi.storage.service.IMmsStoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 仓库管理Controller
 * 
 * @author linyuting
 * @date 2022-03-26
 */
@RestController
@RequestMapping("/storage/store")
public class MmsStoreController extends BaseController
{
    @Autowired
    private IMmsStoreService mmsStoreService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询仓库管理列表
     */
    @PreAuthorize("@ss.hasPermi('storage:store:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsStore mmsStore)
    {
        startPage();
        List<MmsStore> list = mmsStoreService.selectMmsStoreList(mmsStore);
        return getDataTable(list);
    }

    /**
     * 导出仓库管理列表
     */
    @PreAuthorize("@ss.hasPermi('storage:store:export')")
    @Log(title = "仓库管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsStore mmsStore)
    {
        List<MmsStore> list = mmsStoreService.selectMmsStoreList(mmsStore);
        ExcelUtil<MmsStore> util = new ExcelUtil<MmsStore>(MmsStore.class);
        util.exportExcel(response, list, "仓库管理数据");
    }

    /**
     * 获取仓库管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('storage:store:query')")
    @GetMapping(value = "/{storeId}")
    public AjaxResult getInfo(@PathVariable("storeId") Long storeId)
    {
        return AjaxResult.success(mmsStoreService.selectMmsStoreByStoreId(storeId));
    }

    /**
     * 根据proId获取所有仓库信息
     */
    @PreAuthorize("@ss.hasPermi('storage:store:query')")
    @GetMapping(value = "/pro/{proId}")
    public AjaxResult getInfoByProId(@PathVariable("proId") Long proId)
    {
        return AjaxResult.success(mmsStoreService.selectMmsStoreByProId(proId));
    }



    /**
     * 新增仓库管理
     */
    @PreAuthorize("@ss.hasPermi('storage:store:add')")
    @Log(title = "仓库管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsStore mmsStore, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsStore.setUserId(loginUser.getUserId());
        return toAjax(mmsStoreService.insertMmsStore(mmsStore));
    }

    /**
     * 材料入库修改仓库管理
     */
    @PreAuthorize("@ss.hasPermi('storage:store:edit')")
    @Log(title = "材料入库修改仓库管理", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/received", method= RequestMethod.PUT)
    public AjaxResult editDueToReceived(@RequestBody MmsStore mmsStore, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        System.out.printf(mmsStore.toString());
        mmsStore.setUserId(loginUser.getUserId());
        return toAjax(mmsStoreService.updateMmsStoreReceived(mmsStore));
    }


    /**
     * 修改仓库管理
     */
    @PreAuthorize("@ss.hasPermi('storage:store:edit')")
    @Log(title = "仓库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsStore mmsStore, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsStore.setUserId(loginUser.getUserId());
        return toAjax(mmsStoreService.updateMmsStore(mmsStore));
    }
    /**
     * 删除仓库管理
     */
    @PreAuthorize("@ss.hasPermi('storage:store:remove')")
    @Log(title = "仓库管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{storeIds}")
    public AjaxResult remove(@PathVariable Long[] storeIds)
    {
        return toAjax(mmsStoreService.deleteMmsStoreByStoreIds(storeIds));
    }
}
