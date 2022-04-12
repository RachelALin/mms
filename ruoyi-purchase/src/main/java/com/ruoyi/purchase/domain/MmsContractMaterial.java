package com.ruoyi.purchase.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料与合同关联对象 mms_contract_material
 * 
 * @author linyuting
 * @date 2022-03-25
 */
public class MmsContractMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料与合同表ID */
    private Long id;

    /** 材料ID */
    @Excel(name = "材料ID")
    private Long matId;

    /** 合同ID */
    @Excel(name = "合同ID")
    private Long conId;

    /** 材料单价 */
    @Excel(name = "材料单价")
    private Double conUprice;

    /** 材料数量 */
    @Excel(name = "材料数量")
    private Long conNum;

    /** 税率 */
    @Excel(name = "税率")
    private Double conTax;

    /** 含税金额（单价） */
    @Excel(name = "含税金额", readConverterExp = "单=价")
    private Double conTprice;

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
    public void setConId(Long conId) 
    {
        this.conId = conId;
    }

    public Long getConId()
    {
        return conId;
    }

    public Double getConUprice() {
        return conUprice;
    }

    public void setConUprice(Double conUprice) {
        this.conUprice = conUprice;
    }

    public Long getConNum() {
        return conNum;
    }

    public void setConNum(Long conNum) {
        this.conNum = conNum;
    }

    public Double getConTax() {
        return conTax;
    }

    public void setConTax(Double conTax) {
        this.conTax = conTax;
    }

    public Double getConTprice() {
        return conTprice;
    }

    public void setConTprice(Double conTprice) {
        this.conTprice = conTprice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("matId", getMatId())
            .append("conId", getConId())
            .append("conUprice", getConUprice())
            .append("conNum", getConNum())
            .append("conTax", getConTax())
            .append("conTprice", getConTprice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
