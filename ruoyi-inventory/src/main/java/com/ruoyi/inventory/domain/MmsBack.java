package com.ruoyi.inventory.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料退库对象 mms_back
 * 
 * @author linyuting
 * @date 2022-03-28
 */
public class MmsBack extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料退库ID */
    private Long backId;

    /** 材料退库名称 */
    @Excel(name = "材料退库名称")
    private String backName;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long proId;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long storeId;

    /** 材料退库状态 */
    @Excel(name = "材料退库状态")
    private String status;

    /** 审核人ID（只有审核人才能改计划状态） */
    @Excel(name = "审核人ID", readConverterExp = "只=有审核人才能改计划状态")
    private Long userId;

    /** 材料与材料退库关联信息 */
    private List<MmsBackMaterial> mmsBackMaterialList;

    public void setBackId(Long backId) 
    {
        this.backId = backId;
    }

    public Long getBackId() 
    {
        return backId;
    }
    public void setBackName(String backName) 
    {
        this.backName = backName;
    }

    public String getBackName() 
    {
        return backName;
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

    public List<MmsBackMaterial> getMmsBackMaterialList()
    {
        return mmsBackMaterialList;
    }

    public void setMmsBackMaterialList(List<MmsBackMaterial> mmsBackMaterialList)
    {
        this.mmsBackMaterialList = mmsBackMaterialList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("backId", getBackId())
            .append("backName", getBackName())
            .append("proId", getProId())
            .append("storeId", getStoreId())
            .append("status", getStatus())
            .append("userId", getUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("mmsBackMaterialList", getMmsBackMaterialList())
            .toString();
    }
}
