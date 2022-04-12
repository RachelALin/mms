package com.ruoyi.purchase.domain;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.project.domain.MmsProject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料总计划对象 mms_plan
 * 
 * @author linyuting
 * @date 2022-03-23
 */
public class MmsPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划ID */
    private Long planId;

    /** 计划名称 */
    @Excel(name = "计划名称")
    private String planName;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long proId;

    /** 材料总计划状态（0待审核 1已通过 2未通过） */
    @Excel(name = "材料总计划状态", readConverterExp = "0=待审核,1=已通过,2=未通过")
    private String status;

    /** 审核人ID（只有审核人才能改计划状态） */
    @Excel(name = "审核人ID", readConverterExp = "只=有审核人才能改计划状态")
    private Long userId;

    private MmsProject project;

    /** 材料与材料总计划信息 */
    private List<MmsPlanMaterial> mmsPlanMaterialList;

    private SysUser user;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public MmsProject getProject() {
        return project;
    }

    public void setProject(MmsProject project) {
        this.project = project;
    }

    public void setPlanId(Long planId)
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }
    public void setPlanName(String planName) 
    {
        this.planName = planName;
    }

    public String getPlanName() 
    {
        return planName;
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

    public List<MmsPlanMaterial> getMmsPlanMaterialList()
    {
        return mmsPlanMaterialList;
    }

    public void setMmsPlanMaterialList(List<MmsPlanMaterial> mmsPlanMaterialList)
    {
        this.mmsPlanMaterialList = mmsPlanMaterialList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("planId", getPlanId())
            .append("planName", getPlanName())
            .append("proId", getProId())
            .append("status", getStatus())
            .append("userId", getUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("mmsPlanMaterialList", getMmsPlanMaterialList())
            .toString();
    }
}
