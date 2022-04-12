package com.ruoyi.project.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目类型对象 mms_project_type
 * 
 * @author linyuting
 * @date 2022-03-16
 */
public class MmsProjectType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目类型ID */
    private Long id;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String typeName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("typeName", getTypeName())
            .toString();
    }
}
