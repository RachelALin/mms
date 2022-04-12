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
import com.ruoyi.purchase.domain.MmsContract;
import com.ruoyi.purchase.service.IMmsContractService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 材料合同Controller
 * 
 * @author linyuting
 * @date 2022-03-25
 */
@RestController
@RequestMapping("/purchase/contract")
public class MmsContractController extends BaseController
{
    @Autowired
    private IMmsContractService mmsContractService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询材料合同列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:contract:list')")
    @GetMapping("/list")
    public TableDataInfo list(MmsContract mmsContract)
    {
        startPage();
        List<MmsContract> list = mmsContractService.selectMmsContractList(mmsContract);
        return getDataTable(list);
    }

    /**
     * 导出材料合同列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:contract:export')")
    @Log(title = "材料合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MmsContract mmsContract)
    {
        List<MmsContract> list = mmsContractService.selectMmsContractList(mmsContract);
        ExcelUtil<MmsContract> util = new ExcelUtil<MmsContract>(MmsContract.class);
        util.exportExcel(response, list, "材料合同数据");
    }

    /**
     * 获取材料合同详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:contract:query')")
    @GetMapping(value = "/{conId}")
    public AjaxResult getInfo(@PathVariable("conId") Long conId)
    {
        return AjaxResult.success(mmsContractService.selectMmsContractByConId(conId));
    }

    /**
     * 新增材料合同
     */
    @PreAuthorize("@ss.hasPermi('purchase:contract:add')")
    @Log(title = "材料合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MmsContract mmsContract, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsContract.setUserId(loginUser.getUserId());
        return toAjax(mmsContractService.insertMmsContract(mmsContract));
    }

    /**
     * 修改材料合同
     */
    @PreAuthorize("@ss.hasPermi('purchase:contract:edit')")
    @Log(title = "材料合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MmsContract mmsContract, HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        mmsContract.setUserId(loginUser.getUserId());
        return toAjax(mmsContractService.updateMmsContract(mmsContract));
    }

    /**
     * 删除材料合同
     */
    @PreAuthorize("@ss.hasPermi('purchase:contract:remove')")
    @Log(title = "材料合同", businessType = BusinessType.DELETE)
	@DeleteMapping("/{conIds}")
    public AjaxResult remove(@PathVariable Long[] conIds)
    {
        return toAjax(mmsContractService.deleteMmsContractByConIds(conIds));
    }
}
