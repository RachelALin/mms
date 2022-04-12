package com.ruoyi.supplier.service;

import java.util.List;
import com.ruoyi.supplier.domain.MmsSupplier;

/**
 * 供应商管理Service接口
 * 
 * @author linyuting
 * @date 2022-03-18
 */
public interface IMmsSupplierService 
{
    /**
     * 查询供应商管理
     * 
     * @param supplierId 供应商管理主键
     * @return 供应商管理
     */
    public MmsSupplier selectMmsSupplierBySupplierId(Long supplierId);

    /**
     * 查询供应商管理列表
     * 
     * @param mmsSupplier 供应商管理
     * @return 供应商管理集合
     */
    public List<MmsSupplier> selectMmsSupplierList(MmsSupplier mmsSupplier);

    /**
     * 新增供应商管理
     * 
     * @param mmsSupplier 供应商管理
     * @return 结果
     */
    public int insertMmsSupplier(MmsSupplier mmsSupplier);

    /**
     * 修改供应商管理
     * 
     * @param mmsSupplier 供应商管理
     * @return 结果
     */
    public int updateMmsSupplier(MmsSupplier mmsSupplier);

    /**
     * 批量删除供应商管理
     * 
     * @param supplierIds 需要删除的供应商管理主键集合
     * @return 结果
     */
    public int deleteMmsSupplierBySupplierIds(Long[] supplierIds);

    /**
     * 删除供应商管理信息
     * 
     * @param supplierId 供应商管理主键
     * @return 结果
     */
    public int deleteMmsSupplierBySupplierId(Long supplierId);
}
