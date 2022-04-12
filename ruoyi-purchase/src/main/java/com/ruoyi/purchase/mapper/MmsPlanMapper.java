package com.ruoyi.purchase.mapper;

import java.util.List;
import com.ruoyi.purchase.domain.MmsPlan;
import com.ruoyi.purchase.domain.MmsPlanMaterial;

/**
 * 材料总计划Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-23
 */
public interface MmsPlanMapper 
{
    /**
     * 查询材料总计划
     * 
     * @param planId 材料总计划主键
     * @return 材料总计划
     */
    public MmsPlan selectMmsPlanByPlanId(Long planId);

    /**
     * 查询材料总计划列表
     * 
     * @param mmsPlan 材料总计划
     * @return 材料总计划集合
     */
    public List<MmsPlan> selectMmsPlanList(MmsPlan mmsPlan);

    /**
     * 新增材料总计划
     * 
     * @param mmsPlan 材料总计划
     * @return 结果
     */
    public int insertMmsPlan(MmsPlan mmsPlan);

    /**
     * 修改材料总计划
     * 
     * @param mmsPlan 材料总计划
     * @return 结果
     */
    public int updateMmsPlan(MmsPlan mmsPlan);

    /**
     * 删除材料总计划
     * 
     * @param planId 材料总计划主键
     * @return 结果
     */
    public int deleteMmsPlanByPlanId(Long planId);

    /**
     * 批量删除材料总计划
     * 
     * @param planIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsPlanByPlanIds(Long[] planIds);

    /**
     * 批量删除材料与材料总计划
     * 
     * @param planIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsPlanMaterialByPlanIds(Long[] planIds);
    
    /**
     * 批量新增材料与材料总计划
     * 
     * @param mmsPlanMaterialList 材料与材料总计划列表
     * @return 结果
     */
    public int batchMmsPlanMaterial(List<MmsPlanMaterial> mmsPlanMaterialList);
    

    /**
     * 通过材料总计划主键删除材料与材料总计划信息
     * 
     * @param planId 材料总计划ID
     * @return 结果
     */
    public int deleteMmsPlanMaterialByPlanId(Long planId);

//    /**
//     * 通过材料总计划主键查询材料与材料总计划信息
//     *
//     * @param planId 材料总计划ID
//     * @return 结果
//     */
//    public int selectMmsPlanDetailByPlanId(Long planId);
}
