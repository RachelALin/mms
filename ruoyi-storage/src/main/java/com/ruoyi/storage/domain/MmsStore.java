package com.ruoyi.storage.domain;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
//import com.ruoyi.project.domain.MmsProject;
import com.ruoyi.project.domain.MmsProject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 仓库管理对象 mms_store
 * 
 * @author linyuting
 * @date 2022-03-26
 */
public class MmsStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 仓库ID */
    private Long storeId;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long proId;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String storeName;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 仓库电话 */
    @Excel(name = "仓库电话")
    private String storePhone;

    /** 仓库地址 */
    @Excel(name = "仓库地址")
    private String storeAddress;

    /** 仓库状态（0正常 1停用） */
    @Excel(name = "仓库状态", readConverterExp = "0=正常,1=停用")
    private String status;

    private MmsProject project;

    private SysUser user;

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

    /** 材料与仓库关联信息 */
    private List<MmsStoreMaterial> mmsStoreMaterialList;


    private List<MmsStoreReceived> mmsStoreReceivedList;

    public List<MmsStoreReceived> getMmsStoreReceivedList() {
        return mmsStoreReceivedList;
    }

    public void setMmsStoreReceivedList(List<MmsStoreReceived> mmsStoreReceivedList) {
        this.mmsStoreReceivedList = mmsStoreReceivedList;
    }

    public void setStoreId(Long storeId)
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }
    public void setProId(Long proId) 
    {
        this.proId = proId;
    }

    public Long getProId() 
    {
        return proId;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setStorePhone(String storePhone) 
    {
        this.storePhone = storePhone;
    }

    public String getStorePhone() 
    {
        return storePhone;
    }
    public void setStoreAddress(String storeAddress) 
    {
        this.storeAddress = storeAddress;
    }

    public String getStoreAddress() 
    {
        return storeAddress;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public List<MmsStoreMaterial> getMmsStoreMaterialList()
    {
        return mmsStoreMaterialList;
    }

    public void setMmsStoreMaterialList(List<MmsStoreMaterial> mmsStoreMaterialList)
    {
        this.mmsStoreMaterialList = mmsStoreMaterialList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("storeId", getStoreId())
            .append("proId", getProId())
            .append("storeName", getStoreName())
            .append("userId", getUserId())
            .append("storePhone", getStorePhone())
            .append("storeAddress", getStoreAddress())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("mmsStoreMaterialList", getMmsStoreMaterialList())
            .toString();
    }
}
