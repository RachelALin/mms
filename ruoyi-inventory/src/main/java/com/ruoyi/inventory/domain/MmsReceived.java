package com.ruoyi.inventory.domain;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.project.domain.MmsProject;
import com.ruoyi.purchase.domain.MmsContract;
import com.ruoyi.purchase.domain.MmsNeed;
import com.ruoyi.storage.domain.MmsStore;
import com.ruoyi.supplier.domain.MmsSupplier;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料入库对象 mms_received
 * 
 * @author linyuting
 * @date 2022-03-26
 */
public class MmsReceived extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料入库ID */
    private Long inId;

    /** 材料入库名称 */
    @Excel(name = "材料入库名称")
    private String inName;

    /** 材料合同ID */
    @Excel(name = "材料合同ID")
    private Long conId;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long proId;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long supplierId;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long storeId;

    /** 材料入库状态 */
    @Excel(name = "材料入库状态")
    private String status;

    /** 审核人ID（只有审核人才能改计划状态） */
    @Excel(name = "审核人ID", readConverterExp = "只=有审核人才能改计划状态")
    private Long userId;


    private MmsProject project;

    private SysUser user;

//    private MmsNeed need;

    private MmsSupplier supplier;

    private MmsContract contract;

    private MmsStore store;

    /** 材料与材料入库关联信息 */
    private List<MmsReceivedMaterial> mmsReceivedMaterialList;

    public MmsStore getStore() {
        return store;
    }

    public void setStore(MmsStore store) {
        this.store = store;
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

//    public MmsNeed getNeed() {
//        return need;
//    }
//
//    public void setNeed(MmsNeed need) {
//        this.need = need;
//    }

    public MmsSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(MmsSupplier supplier) {
        this.supplier = supplier;
    }

    public MmsContract getContract() {
        return contract;
    }

    public void setContract(MmsContract contract) {
        this.contract = contract;
    }

    public void setInId(Long inId)
    {
        this.inId = inId;
    }

    public Long getInId() 
    {
        return inId;
    }
    public void setInName(String inName) 
    {
        this.inName = inName;
    }

    public String getInName() 
    {
        return inName;
    }
    public void setConId(Long conId) 
    {
        this.conId = conId;
    }

    public Long getConId() 
    {
        return conId;
    }
    public void setProId(Long proId) 
    {
        this.proId = proId;
    }

    public Long getProId() 
    {
        return proId;
    }
    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
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

    public List<MmsReceivedMaterial> getMmsReceivedMaterialList()
    {
        return mmsReceivedMaterialList;
    }

    public void setMmsReceivedMaterialList(List<MmsReceivedMaterial> mmsReceivedMaterialList)
    {
        this.mmsReceivedMaterialList = mmsReceivedMaterialList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("inId", getInId())
            .append("inName", getInName())
            .append("conId", getConId())
            .append("proId", getProId())
            .append("supplierId", getSupplierId())
            .append("storeId", getStoreId())
            .append("status", getStatus())
            .append("userId", getUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("mmsReceivedMaterialList", getMmsReceivedMaterialList())
            .toString();
    }
}
