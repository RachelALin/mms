package com.ruoyi.supplier.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商管理对象 mms_supplier
 * 
 * @author linyuting
 * @date 2022-03-18
 */
public class MmsSupplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商ID */
    private Long supplierId;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 供应商开户行 */
    @Excel(name = "供应商开户行")
    private String supplierBank;

    /** 供应商银行账号 */
    @Excel(name = "供应商银行账号")
    private String supplierCard;

    /** 卡号户头 */
    @Excel(name = "卡号户头")
    private String cardName;

    /** 供应商电话 */
    @Excel(name = "供应商电话")
    private String supplierPhone;

    /** 供应商地址 */
    @Excel(name = "供应商地址")
    private String supplierAddress;

    /** 供应商类型ID */
    @Excel(name = "供应商类型ID")
    private Long stypeId;

    private MmsSupplierType supplierType;

    public MmsSupplierType getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(MmsSupplierType supplierType) {
        this.supplierType = supplierType;
    }

    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setSupplierBank(String supplierBank) 
    {
        this.supplierBank = supplierBank;
    }

    public String getSupplierBank() 
    {
        return supplierBank;
    }
    public void setSupplierCard(String supplierCard) 
    {
        this.supplierCard = supplierCard;
    }

    public String getSupplierCard() 
    {
        return supplierCard;
    }
    public void setCardName(String cardName) 
    {
        this.cardName = cardName;
    }

    public String getCardName() 
    {
        return cardName;
    }
    public void setSupplierPhone(String supplierPhone) 
    {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierPhone() 
    {
        return supplierPhone;
    }
    public void setSupplierAddress(String supplierAddress) 
    {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierAddress() 
    {
        return supplierAddress;
    }
    public void setStypeId(Long stypeId) 
    {
        this.stypeId = stypeId;
    }

    public Long getStypeId() 
    {
        return stypeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("supplierId", getSupplierId())
            .append("supplierName", getSupplierName())
            .append("supplierBank", getSupplierBank())
            .append("supplierCard", getSupplierCard())
            .append("cardName", getCardName())
            .append("supplierPhone", getSupplierPhone())
            .append("supplierAddress", getSupplierAddress())
            .append("stypeId", getStypeId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
