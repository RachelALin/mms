package com.ruoyi.supplier.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.supplier.mapper.MmsSupplierTypeMapper;
import com.ruoyi.supplier.domain.MmsSupplierType;
import com.ruoyi.supplier.service.IMmsSupplierTypeService;

/**
 * 供应商类型管理Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-18
 */
@Service
public class MmsSupplierTypeServiceImpl implements IMmsSupplierTypeService 
{
    @Autowired
    private MmsSupplierTypeMapper mmsSupplierTypeMapper;

    /**
     * 查询供应商类型管理
     * 
     * @param id 供应商类型管理主键
     * @return 供应商类型管理
     */
    @Override
    public MmsSupplierType selectMmsSupplierTypeById(Long id)
    {
        return mmsSupplierTypeMapper.selectMmsSupplierTypeById(id);
    }

    /**
     * 查询供应商类型管理列表
     * 
     * @param mmsSupplierType 供应商类型管理
     * @return 供应商类型管理
     */
    @Override
    public List<MmsSupplierType> selectMmsSupplierTypeList(MmsSupplierType mmsSupplierType)
    {
        return mmsSupplierTypeMapper.selectMmsSupplierTypeList(mmsSupplierType);
    }

    /**
     * 新增供应商类型管理
     * 
     * @param mmsSupplierType 供应商类型管理
     * @return 结果
     */
    @Override
    public int insertMmsSupplierType(MmsSupplierType mmsSupplierType)
    {
        mmsSupplierType.setCreateTime(DateUtils.getNowDate());
        return mmsSupplierTypeMapper.insertMmsSupplierType(mmsSupplierType);
    }

    /**
     * 修改供应商类型管理
     * 
     * @param mmsSupplierType 供应商类型管理
     * @return 结果
     */
    @Override
    public int updateMmsSupplierType(MmsSupplierType mmsSupplierType)
    {
        mmsSupplierType.setUpdateTime(DateUtils.getNowDate());
        return mmsSupplierTypeMapper.updateMmsSupplierType(mmsSupplierType);
    }

    /**
     * 批量删除供应商类型管理
     * 
     * @param ids 需要删除的供应商类型管理主键
     * @return 结果
     */
    @Override
    public int deleteMmsSupplierTypeByIds(Long[] ids)
    {
        return mmsSupplierTypeMapper.deleteMmsSupplierTypeByIds(ids);
    }

    /**
     * 删除供应商类型管理信息
     * 
     * @param id 供应商类型管理主键
     * @return 结果
     */
    @Override
    public int deleteMmsSupplierTypeById(Long id)
    {
        return mmsSupplierTypeMapper.deleteMmsSupplierTypeById(id);
    }
}
