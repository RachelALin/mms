package com.ruoyi.supplier.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.supplier.mapper.MmsSupplierMapper;
import com.ruoyi.supplier.domain.MmsSupplier;
import com.ruoyi.supplier.service.IMmsSupplierService;

/**
 * 供应商管理Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-18
 */
@Service
public class MmsSupplierServiceImpl implements IMmsSupplierService 
{
    @Autowired
    private MmsSupplierMapper mmsSupplierMapper;

    /**
     * 查询供应商管理
     * 
     * @param supplierId 供应商管理主键
     * @return 供应商管理
     */
    @Override
    public MmsSupplier selectMmsSupplierBySupplierId(Long supplierId)
    {
        return mmsSupplierMapper.selectMmsSupplierBySupplierId(supplierId);
    }

    /**
     * 查询供应商管理列表
     * 
     * @param mmsSupplier 供应商管理
     * @return 供应商管理
     */
    @Override
    public List<MmsSupplier> selectMmsSupplierList(MmsSupplier mmsSupplier)
    {
        return mmsSupplierMapper.selectMmsSupplierList(mmsSupplier);
    }

    /**
     * 新增供应商管理
     * 
     * @param mmsSupplier 供应商管理
     * @return 结果
     */
    @Override
    public int insertMmsSupplier(MmsSupplier mmsSupplier)
    {
        mmsSupplier.setCreateTime(DateUtils.getNowDate());
        return mmsSupplierMapper.insertMmsSupplier(mmsSupplier);
    }

    /**
     * 修改供应商管理
     * 
     * @param mmsSupplier 供应商管理
     * @return 结果
     */
    @Override
    public int updateMmsSupplier(MmsSupplier mmsSupplier)
    {
        mmsSupplier.setUpdateTime(DateUtils.getNowDate());
        return mmsSupplierMapper.updateMmsSupplier(mmsSupplier);
    }

    /**
     * 批量删除供应商管理
     * 
     * @param supplierIds 需要删除的供应商管理主键
     * @return 结果
     */
    @Override
    public int deleteMmsSupplierBySupplierIds(Long[] supplierIds)
    {
        return mmsSupplierMapper.deleteMmsSupplierBySupplierIds(supplierIds);
    }

    /**
     * 删除供应商管理信息
     * 
     * @param supplierId 供应商管理主键
     * @return 结果
     */
    @Override
    public int deleteMmsSupplierBySupplierId(Long supplierId)
    {
        return mmsSupplierMapper.deleteMmsSupplierBySupplierId(supplierId);
    }
}
