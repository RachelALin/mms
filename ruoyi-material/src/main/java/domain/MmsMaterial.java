package com.ruoyi.material.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料管理对象 mms_material
 * 
 * @author linyuting
 * @date 2022-03-23
 */
public class MmsMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料ID */
    private Long matId;

    /** 材料名称 */
    @Excel(name = "材料名称")
    private String matName;

    /** 材料规格 */
    @Excel(name = "材料规格")
    private String matSize;

    /** 材料单位 */
    @Excel(name = "材料单位")
    private String matUnit;

    /** 材料类型 */
    @Excel(name = "材料类型")
    private String matType;

    public void setMatId(Long matId) 
    {
        this.matId = matId;
    }

    public Long getMatId() 
    {
        return matId;
    }
    public void setMatName(String matName) 
    {
        this.matName = matName;
    }

    public String getMatName() 
    {
        return matName;
    }

    public String getMatSize() {
        return matSize;
    }

    public void setMatSize(String matSize) {
        this.matSize = matSize;
    }

    public void setMatUnit(String matUnit)
    {
        this.matUnit = matUnit;
    }

    public String getMatUnit() 
    {
        return matUnit;
    }
    public void setMatType(String matType) 
    {
        this.matType = matType;
    }

    public String getMatType() 
    {
        return matType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("matId", getMatId())
            .append("matName", getMatName())
            .append("matSize", getMatSize())
            .append("matUnit", getMatUnit())
            .append("matType", getMatType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
