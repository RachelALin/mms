package com.ruoyi.inventory.domain;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.project.domain.MmsProject;
import com.ruoyi.storage.domain.MmsStore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料调拨对象 mms_change
 * 
 * @author linyuting
 * @date 2022-04-26
 */
public class MmsChange extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料调拨ID */
    private Long chaId;

    /** 材料调拨名称 */
    @Excel(name = "材料调拨名称")
    private String chaName;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long proId;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long storeId;

    /** 材料调拨状态（0待审核 1未通过 2已通过） */
    @Excel(name = "材料调拨状态", readConverterExp = "0=待审核,1=未通过,2=已通过")
    private String status;

    /** 审核人ID（只有审核人才能改计划状态） */
    @Excel(name = "审核人ID", readConverterExp = "只=有审核人才能改计划状态")
    private Long userId;
    private MmsProject project;

    private SysUser user;

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

    public MmsStore getStore() {
        return store;
    }

    public void setStore(MmsStore store) {
        this.store = store;
    }

    /** 材料与材料调拨关联信息 */
    private List<MmsChangeMaterial> mmsChangeMaterialList;

    public void setChaId(Long chaId) 
    {
        this.chaId = chaId;
    }

    public Long getChaId() 
    {
        return chaId;
    }
    public void setChaName(String chaName) 
    {
        this.chaName = chaName;
    }

    public String getChaName() 
    {
        return chaName;
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

    public List<MmsChangeMaterial> getMmsChangeMaterialList()
    {
        return mmsChangeMaterialList;
    }

    public void setMmsChangeMaterialList(List<MmsChangeMaterial> mmsChangeMaterialList)
    {
        this.mmsChangeMaterialList = mmsChangeMaterialList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("chaId", getChaId())
            .append("chaName", getChaName())
            .append("proId", getProId())
            .append("storeId", getStoreId())
            .append("status", getStatus())
            .append("userId", getUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("mmsChangeMaterialList", getMmsChangeMaterialList())
            .toString();
    }
}
