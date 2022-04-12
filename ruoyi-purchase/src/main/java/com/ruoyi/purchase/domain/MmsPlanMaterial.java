package com.ruoyi.purchase.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料与材料总计划对象 mms_plan_material
 * 
 * @author linyuting
 * @date 2022-03-23
 */
public class MmsPlanMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料与材料总计划表ID */
    private Long id;

    /** 材料ID */
    @Excel(name = "材料ID")
    private Long matId;

    /** 计划ID */
    @Excel(name = "计划ID")
    private Long planId;

    /** 计划数量 */
    @Excel(name = "计划数量")
    private Long planNum;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMatId(Long matId) 
    {
        this.matId = matId;
    }

    public Long getMatId() 
    {
        return matId;
    }
    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }
    public void setPlanNum(Long planNum) 
    {
        this.planNum = planNum;
    }

    public Long getPlanNum() 
    {
        return planNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("matId", getMatId())
            .append("planId", getPlanId())
            .append("planNum", getPlanNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
