package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MmsMaterial;

/**
 * 材料管理Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-23
 */
public interface MmsMaterialMapper 
{
    /**
     * 查询材料管理
     * 
     * @param matId 材料管理主键
     * @return 材料管理
     */
    public MmsMaterial selectMmsMaterialByMatId(Long matId);

    /**
     * 查询材料管理列表
     * 
     * @param mmsMaterial 材料管理
     * @return 材料管理集合
     */
    public List<MmsMaterial> selectMmsMaterialList(MmsMaterial mmsMaterial);

    /**
     * 新增材料管理
     * 
     * @param mmsMaterial 材料管理
     * @return 结果
     */
    public int insertMmsMaterial(MmsMaterial mmsMaterial);

    /**
     * 修改材料管理
     * 
     * @param mmsMaterial 材料管理
     * @return 结果
     */
    public int updateMmsMaterial(MmsMaterial mmsMaterial);

    /**
     * 删除材料管理
     * 
     * @param matId 材料管理主键
     * @return 结果
     */
    public int deleteMmsMaterialByMatId(Long matId);

    /**
     * 批量删除材料管理
     * 
     * @param matIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsMaterialByMatIds(Long[] matIds);
}
