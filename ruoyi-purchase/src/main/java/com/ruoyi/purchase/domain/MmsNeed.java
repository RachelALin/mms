package com.ruoyi.purchase.domain;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.project.domain.MmsProject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料需用计划对象 mms_need
 * 
 * @author linyuting
 * @date 2022-03-24
 */
public class MmsNeed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 需用计划ID */
    private Long needId;

    /** 需用计划名称 */
    @Excel(name = "需用计划名称")
    private String needName;

    /** 原计划ID */
    @Excel(name = "原计划ID")
    private Long planId;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long proId;

    /** 材料总计划状态（0待审核 1已通过 2未通过） */
    @Excel(name = "材料总计划状态", readConverterExp = "0=待审核,1=已通过,2=未通过")
    private String status;

    /** 审核人ID（只有审核人才能改计划状态） */
    @Excel(name = "审核人ID", readConverterExp = "只=有审核人才能改计划状态")
    private Long userId;

    /** 材料与材料需用计划信息 */
    private List<MmsNeedMaterial> mmsNeedMaterialList;

    private MmsProject project;

    private SysUser user;

    private MmsPlan plan;

    public MmsProject getProject() {
        return project;
    }

    public void setProject(MmsProject project) {
        this.project = project;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public MmsPlan getPlan() {
        return plan;
    }

    public void setPlan(MmsPlan plan) {
        this.plan = plan;
    }

    public void setNeedId(Long needId)
    {
        this.needId = needId;
    }

    public Long getNeedId() 
    {
        return needId;
    }
    public void setNeedName(String needName) 
    {
        this.needName = needName;
    }

    public String getNeedName() 
    {
        return needName;
    }
    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }
    public void setProId(Long proId) 
    {
        this.proId = proId;
    }

    public Long getProId() 
    {
        return proId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public List<MmsNeedMaterial> getMmsNeedMaterialList()
    {
        return mmsNeedMaterialList;
    }

    public void setMmsNeedMaterialList(List<MmsNeedMaterial> mmsNeedMaterialList)
    {
        this.mmsNeedMaterialList = mmsNeedMaterialList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("needId", getNeedId())
            .append("needName", getNeedName())
            .append("planId", getPlanId())
            .append("proId", getProId())
            .append("status", getStatus())
            .append("userId", getUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("mmsNeedMaterialList", getMmsNeedMaterialList())
            .toString();
    }
}
