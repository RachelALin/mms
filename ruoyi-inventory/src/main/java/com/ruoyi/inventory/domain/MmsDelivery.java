package com.ruoyi.inventory.domain;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.project.domain.MmsProject;
import com.ruoyi.purchase.domain.MmsContract;
import com.ruoyi.storage.domain.MmsStore;
import com.ruoyi.supplier.domain.MmsSupplier;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料出库对象 mms_delivery
 * 
 * @author linyuting
 * @date 2022-03-28
 */
public class MmsDelivery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料出库ID */
    private Long outId;

    /** 材料出库名称 */
    @Excel(name = "材料出库名称")
    private String outName;

    /** 材料入库ID */
    @Excel(name = "材料入库ID")
    private Long inId;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long proId;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long storeId;

    /** 材料出库状态 */
    @Excel(name = "材料出库状态")
    private String status;

    /** 审核人ID（只有审核人才能改计划状态） */
    @Excel(name = "审核人ID", readConverterExp = "只=有审核人才能改计划状态")
    private Long userId;
    private MmsProject project;

    private SysUser user;

    private MmsReceived received;

    private MmsStore store;

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

    public MmsReceived getReceived() {
        return received;
    }

    public void setReceived(MmsReceived received) {
        this.received = received;
    }

    public MmsStore getStore() {
        return store;
    }

    public void setStore(MmsStore store) {
        this.store = store;
    }

    /** 材料与材料出库关联信息 */
    private List<MmsDeliveryMaterial> mmsDeliveryMaterialList;

    public void setOutId(Long outId) 
    {
        this.outId = outId;
    }

    public Long getOutId() 
    {
        return outId;
    }
    public void setOutName(String outName) 
    {
        this.outName = outName;
    }

    public String getOutName() 
    {
        return outName;
    }
    public void setInId(Long inId) 
    {
        this.inId = inId;
    }

    public Long getInId() 
    {
        return inId;
    }
    public void setProId(Long proId) 
    {
        this.proId = proId;
    }

    public Long getProId() 
    {
        return proId;
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

    public List<MmsDeliveryMaterial> getMmsDeliveryMaterialList()
    {
        return mmsDeliveryMaterialList;
    }

    public void setMmsDeliveryMaterialList(List<MmsDeliveryMaterial> mmsDeliveryMaterialList)
    {
        this.mmsDeliveryMaterialList = mmsDeliveryMaterialList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("outId", getOutId())
            .append("outName", getOutName())
            .append("inId", getInId())
            .append("proId", getProId())
            .append("storeId", getStoreId())
            .append("status", getStatus())
            .append("userId", getUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("mmsDeliveryMaterialList", getMmsDeliveryMaterialList())
            .toString();
    }
}
