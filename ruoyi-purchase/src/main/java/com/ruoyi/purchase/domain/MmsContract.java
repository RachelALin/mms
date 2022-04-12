package com.ruoyi.purchase.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.project.domain.MmsProject;
import com.ruoyi.supplier.domain.MmsSupplier;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料合同对象 mms_contract
 * 
 * @author linyuting
 * @date 2022-03-25
 */
public class MmsContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同ID */
    private Long conId;

    /** 合同名称 */
    @Excel(name = "合同名称")
    private String conName;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long proId;

    /** 需用计划ID */
    @Excel(name = "需用计划ID")
    private Long needId;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long supplierId;

    /** 录入人ID */
    @Excel(name = "录入人ID")
    private Long userId;

    /** 支付方式 1现金 2转账 3支票 4微信） */
    @Excel(name = "支付方式 1现金 2转账 3支票 4微信）")
    private String paymentWay;

    /** 交货方式 1一次性交货 2分批交货） */
    @Excel(name = "交货方式 1一次性交货 2分批交货）")
    private String deliveryWay;

    /** 结算方式 1分段结算 2合同结算 3竣工后一次结算） */
    @Excel(name = "结算方式 1分段结算 2合同结算 3竣工后一次结算）")
    private String clearingForm;

    /** 开工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 竣工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "竣工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;


    private MmsProject project;

    private SysUser user;

    private MmsNeed need;

    private MmsSupplier supplier;

    public MmsSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(MmsSupplier supplier) {
        this.supplier = supplier;
    }

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

    public MmsNeed getNeed() {
        return need;
    }

    public void setNeed(MmsNeed need) {
        this.need = need;
    }

    /** 材料与合同关联信息 */
    private List<MmsContractMaterial> mmsContractMaterialList;

    public void setConId(Long conId) 
    {
        this.conId = conId;
    }

    public Long getConId() 
    {
        return conId;
    }
    public void setConName(String conName) 
    {
        this.conName = conName;
    }

    public String getConName() 
    {
        return conName;
    }
    public void setProId(Long proId) 
    {
        this.proId = proId;
    }

    public Long getProId() 
    {
        return proId;
    }
    public void setNeedId(Long needId) 
    {
        this.needId = needId;
    }

    public Long getNeedId() 
    {
        return needId;
    }
    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setPaymentWay(String paymentWay) 
    {
        this.paymentWay = paymentWay;
    }

    public String getPaymentWay() 
    {
        return paymentWay;
    }
    public void setDeliveryWay(String deliveryWay) 
    {
        this.deliveryWay = deliveryWay;
    }

    public String getDeliveryWay() 
    {
        return deliveryWay;
    }
    public void setClearingForm(String clearingForm) 
    {
        this.clearingForm = clearingForm;
    }

    public String getClearingForm() 
    {
        return clearingForm;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }

    public List<MmsContractMaterial> getMmsContractMaterialList()
    {
        return mmsContractMaterialList;
    }

    public void setMmsContractMaterialList(List<MmsContractMaterial> mmsContractMaterialList)
    {
        this.mmsContractMaterialList = mmsContractMaterialList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("conId", getConId())
            .append("conName", getConName())
            .append("proId", getProId())
            .append("needId", getNeedId())
            .append("supplierId", getSupplierId())
            .append("userId", getUserId())
            .append("paymentWay", getPaymentWay())
            .append("deliveryWay", getDeliveryWay())
            .append("clearingForm", getClearingForm())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("mmsContractMaterialList", getMmsContractMaterialList())
            .toString();
    }
}
