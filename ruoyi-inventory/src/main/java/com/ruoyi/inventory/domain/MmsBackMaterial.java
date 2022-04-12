package com.ruoyi.inventory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料与材料退库关联对象 mms_back_material
 * 
 * @author linyuting
 * @date 2022-03-28
 */
public class MmsBackMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料与材料退库关联表ID */
    private Long id;

    /** 材料ID */
    @Excel(name = "材料ID")
    private Long matId;

    /** 材料出库ID */
    @Excel(name = "材料出库ID")
    private Long backId;

    /** 出库数量 */
    @Excel(name = "出库数量")
    private Long backNum;

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
    public void setBackId(Long backId) 
    {
        this.backId = backId;
    }

    public Long getBackId() 
    {
        return backId;
    }
    public void setBackNum(Long backNum) 
    {
        this.backNum = backNum;
    }

    public Long getBackNum() 
    {
        return backNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("matId", getMatId())
            .append("backId", getBackId())
            .append("backNum", getBackNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
