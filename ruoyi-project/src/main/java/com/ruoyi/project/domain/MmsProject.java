package com.ruoyi.project.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.SysUser;
//import com.ruoyi.storage.domain.MmsStore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目立项对象 mms_project
 * 
 * @author linyuting
 * @date 2022-03-16
 */
public class MmsProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目ID */
    private Long proId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String proName;

    /** 工程类型ID */
    @Excel(name = "工程类型ID")
    private Long ptypeId;

    /** 工程面积 */
    @Excel(name = "工程面积")
    private Long proArea;

    /** 建设单位 */
    @Excel(name = "建设单位")
    private String proUnit;

    /** 监督单位 */
    @Excel(name = "监督单位")
    private String supervisionUnit;

    /** 工程地点 */
    @Excel(name = "工程地点")
    private String proAddress;

    /** 工程电话 */
    @Excel(name = "工程电话")
    private String proPhone;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 工程状态（0筹备 1在建 2竣工 3结算） */
    @Excel(name = "工程状态", readConverterExp = "0=筹备,1=在建,2=竣工,3=结算")
    private String status;

    /** 开工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 竣工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "竣工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 项目备注 */
    @Excel(name = "项目备注")
    private String proNote;

    /** 计税方式（0一般征税 1简易征税） */
    @Excel(name = "计税方式", readConverterExp = "0=一般征税,1=简易征税")
    private String taxWay;

//    @Excel(name = "")
    private SysUser user;

//    private MmsStore store;
    private MmsProjectType projectType;

//    public MmsStore getStore() {
//        return store;
//    }
//
//    public void setStore(MmsStore store) {
//        this.store = store;
//    }

    public MmsProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(MmsProjectType projectType) {
        this.projectType = projectType;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public Long getProId()
    {
        return proId;
    }
    public void setProName(String proName) 
    {
        this.proName = proName;
    }

    public String getProName() 
    {
        return proName;
    }
    public void setPtypeId(Long ptypeId) 
    {
        this.ptypeId = ptypeId;
    }

    public Long getPtypeId() 
    {
        return ptypeId;
    }
    public void setProArea(Long proArea) 
    {
        this.proArea = proArea;
    }

    public Long getProArea() 
    {
        return proArea;
    }
    public void setProUnit(String proUnit) 
    {
        this.proUnit = proUnit;
    }

    public String getProUnit() 
    {
        return proUnit;
    }
    public void setSupervisionUnit(String supervisionUnit) 
    {
        this.supervisionUnit = supervisionUnit;
    }

    public String getSupervisionUnit() 
    {
        return supervisionUnit;
    }
    public void setProAddress(String proAddress) 
    {
        this.proAddress = proAddress;
    }

    public String getProAddress() 
    {
        return proAddress;
    }
    public void setProPhone(String proPhone) 
    {
        this.proPhone = proPhone;
    }

    public String getProPhone() 
    {
        return proPhone;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
    public void setProNote(String proNote) 
    {
        this.proNote = proNote;
    }

    public String getProNote() 
    {
        return proNote;
    }
    public void setTaxWay(String taxWay) 
    {
        this.taxWay = taxWay;
    }

    public String getTaxWay() 
    {
        return taxWay;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("proId", getProId())
            .append("proName", getProName())
            .append("ptypeId", getPtypeId())
            .append("proArea", getProArea())
            .append("proUnit", getProUnit())
            .append("supervisionUnit", getSupervisionUnit())
            .append("proAddress", getProAddress())
            .append("proPhone", getProPhone())
            .append("userId", getUserId())
            .append("status", getStatus())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("proNote", getProNote())
            .append("taxWay", getTaxWay())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
