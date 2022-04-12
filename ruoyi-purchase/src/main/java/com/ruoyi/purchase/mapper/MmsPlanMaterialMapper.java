package com.ruoyi.purchase.mapper;

import java.util.List;
import com.ruoyi.purchase.domain.MmsPlanMaterial;

/**
 * 材料与材料总计划Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-24
 */
public interface MmsPlanMaterialMapper 
{
    /**
     * 查询材料与材料总计划
     * 
     * @param id 材料与材料总计划主键
     * @return 材料与材料总计划
     */
    public MmsPlanMaterial selectMmsPlanMaterialById(Long id);

    /**
     * 查询材料与材料总计划列表
     * 
     * @param mmsPlanMaterial 材料与材料总计划
     * @return 材料与材料总计划集合
     */
    public List<MmsPlanMaterial> selectMmsPlanMaterialList(MmsPlanMaterial mmsPlanMaterial);

    /**
     * 新增材料与材料总计划
     * 
     * @param mmsPlanMaterial 材料与材料总计划
     * @return 结果
     */
    public int insertMmsPlanMaterial(MmsPlanMaterial mmsPlanMaterial);

    /**
     * 修改材料与材料总计划
     * 
     * @param mmsPlanMaterial 材料与材料总计划
     * @return 结果
     */
    public int updateMmsPlanMaterial(MmsPlanMaterial mmsPlanMaterial);

    /**
     * 删除材料与材料总计划
     * 
     * @param id 材料与材料总计划主键
     * @return 结果
     */
    public int deleteMmsPlanMaterialById(Long id);

    /**
     * 批量删除材料与材料总计划
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsPlanMaterialByIds(Long[] ids);
}
