package com.ruoyi.supplier.mapper;

import java.util.List;
import com.ruoyi.supplier.domain.MmsSupplierType;

/**
 * 供应商类型管理Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-18
 */
public interface MmsSupplierTypeMapper 
{
    /**
     * 查询供应商类型管理
     * 
     * @param id 供应商类型管理主键
     * @return 供应商类型管理
     */
    public MmsSupplierType selectMmsSupplierTypeById(Long id);

    /**
     * 查询供应商类型管理列表
     * 
     * @param mmsSupplierType 供应商类型管理
     * @return 供应商类型管理集合
     */
    public List<MmsSupplierType> selectMmsSupplierTypeList(MmsSupplierType mmsSupplierType);

    /**
     * 新增供应商类型管理
     * 
     * @param mmsSupplierType 供应商类型管理
     * @return 结果
     */
    public int insertMmsSupplierType(MmsSupplierType mmsSupplierType);

    /**
     * 修改供应商类型管理
     * 
     * @param mmsSupplierType 供应商类型管理
     * @return 结果
     */
    public int updateMmsSupplierType(MmsSupplierType mmsSupplierType);

    /**
     * 删除供应商类型管理
     * 
     * @param id 供应商类型管理主键
     * @return 结果
     */
    public int deleteMmsSupplierTypeById(Long id);

    /**
     * 批量删除供应商类型管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsSupplierTypeByIds(Long[] ids);
}
