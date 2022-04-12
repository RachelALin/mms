package com.ruoyi.inventory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料与材料盘点关联对象 mms_check_material
 * 
 * @author linyuting
 * @date 2022-03-28
 */
public class MmsCheckMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料与材料盘点关联表ID */
    private Long id;

    /** 材料ID */
    @Excel(name = "材料ID")
    private Long matId;

    /** 材料盘点ID */
    @Excel(name = "材料盘点ID")
    private Long cheId;

    /** 账存数量 */
    @Excel(name = "账存数量")
    private Long num;

    /** 盘点数量 */
    @Excel(name = "盘点数量")
    private Long cheNum;

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
    public void setCheId(Long cheId) 
    {
        this.cheId = cheId;
    }

    public Long getCheId() 
    {
        return cheId;
    }
    public void setNum(Long num) 
    {
        this.num = num;
    }

    public Long getNum() 
    {
        return num;
    }
    public void setCheNum(Long cheNum) 
    {
        this.cheNum = cheNum;
    }

    public Long getCheNum() 
    {
        return cheNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("matId", getMatId())
            .append("cheId", getCheId())
            .append("num", getNum())
            .append("cheNum", getCheNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
