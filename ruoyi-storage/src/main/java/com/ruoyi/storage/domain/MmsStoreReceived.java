package com.ruoyi.storage.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.project.domain.MmsProject;

public class MmsStoreReceived extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /** 材料与仓库表ID */
    private Long id;

    /** 材料ID */
    @Excel(name = "材料ID")
    private Long matId;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long storeId;

    /** 材料单价 */

    @Excel(name = "材料单价")
    private Long conUprice;

    /** 材料数量 */
    @Excel(name = "材料数量")
    private Long inNum;

    /** 材料总价 */
    @Excel(name = "材料总价")
    private Long conTprice;


    private SysUser user;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatId() {
        return matId;
    }

    public void setMatId(Long matId) {
        this.matId = matId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getConUprice() {
        return conUprice;
    }

    public void setConUprice(Long conUprice) {
        this.conUprice = conUprice;
    }

    public Long getInNum() {
        return inNum;
    }

    public void setInNum(Long inNum) {
        this.inNum = inNum;
    }

    public Long getConTprice() {
        return conTprice;
    }

    public void setConTprice(Long conTprice) {
        this.conTprice = conTprice;
    }

    @Override
    public String toString() {
        return "MmsStoreReceived{" +
                "id=" + id +
                ", matId=" + matId +
                ", storeId=" + storeId +
                ", conUprice=" + conUprice +
                ", inNum=" + inNum +
                ", conTprice=" + conTprice +
                '}';
    }
}
