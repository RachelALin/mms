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
 * 材料盘点对象 mms_check
 * 
 * @author linyuting
 * @date 2022-04-26
 */
public class MmsCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料盘点ID */
    private Long cheId;

    /** 材料盘点名称 */
    @Excel(name = "材料盘点名称")
    private String cheName;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long proId;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long storeId;

    /** 材料盘点状态（0待审核 1已通过 2未通过） */
    @Excel(name = "材料盘点状态", readConverterExp = "0=待审核,1=已通过,2=未通过")
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

    /** 材料与材料盘点关联信息 */
    private List<MmsCheckMaterial> mmsCheckMaterialList;

    public void setCheId(Long cheId) 
    {
        this.cheId = cheId;
    }

    public Long getCheId() 
    {
        return cheId;
    }
    public void setCheName(String cheName) 
    {
        this.cheName = cheName;
    }

    public String getCheName() 
    {
        return cheName;
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

    public List<MmsCheckMaterial> getMmsCheckMaterialList()
    {
        return mmsCheckMaterialList;
    }

    public void setMmsCheckMaterialList(List<MmsCheckMaterial> mmsCheckMaterialList)
    {
        this.mmsCheckMaterialList = mmsCheckMaterialList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cheId", getCheId())
            .append("cheName", getCheName())
            .append("proId", getProId())
            .append("storeId", getStoreId())
            .append("status", getStatus())
            .append("userId", getUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("mmsCheckMaterialList", getMmsCheckMaterialList())
            .toString();
    }
}
