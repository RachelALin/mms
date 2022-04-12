package com.ruoyi.purchase.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料与材料需用计划对象 mms_need_material
 * 
 * @author linyuting
 * @date 2022-03-24
 */
public class MmsNeedMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料与材料需用计划表ID */
    private Long id;

    /** 材料ID */
    @Excel(name = "材料ID")
    private Long matId;

    /** 需用计划ID */
    @Excel(name = "需用计划ID")
    private Long needId;

    /** 材料单价 */
    @Excel(name = "材料单价")
    private Long matUprice;

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
    public void setNeedId(Long needId) 
    {
        this.needId = needId;
    }

    public Long getNeedId() 
    {
        return needId;
    }
    public void setMatUprice(Long matUprice) 
    {
        this.matUprice = matUprice;
    }

    public Long getMatUprice() 
    {
        return matUprice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("matId", getMatId())
            .append("needId", getNeedId())
            .append("matUprice", getMatUprice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
