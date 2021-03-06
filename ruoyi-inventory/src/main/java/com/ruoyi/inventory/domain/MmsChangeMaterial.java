package com.ruoyi.inventory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料与材料调拨关联对象 mms_change_material
 * 
 * @author linyuting
 * @date 2022-04-26
 */
public class MmsChangeMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料与材料调拨关联表ID */
    private Long id;

    /** 材料ID */
    @Excel(name = "材料ID")
    private Long matId;

    /** 材料调拨ID */
    @Excel(name = "材料调拨ID")
    private Long chaId;

    /** 材料单价 */
    @Excel(name = "材料单价")
    private Long matUprice;

    /** 材料数量 */
    @Excel(name = "材料数量")
    private Long matNum;

    /** 材料总价 */
    @Excel(name = "材料总价")
    private Long matTprice;

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
    public void setChaId(Long chaId) 
    {
        this.chaId = chaId;
    }

    public Long getChaId() 
    {
        return chaId;
    }
    public void setMatUprice(Long matUprice) 
    {
        this.matUprice = matUprice;
    }

    public Long getMatUprice() 
    {
        return matUprice;
    }
    public void setMatNum(Long matNum) 
    {
        this.matNum = matNum;
    }

    public Long getMatNum() 
    {
        return matNum;
    }
    public void setMatTprice(Long matTprice) 
    {
        this.matTprice = matTprice;
    }

    public Long getMatTprice() 
    {
        return matTprice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("matId", getMatId())
            .append("chaId", getChaId())
            .append("matUprice", getMatUprice())
            .append("matNum", getMatNum())
            .append("matTprice", getMatTprice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
