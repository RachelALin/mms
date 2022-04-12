package com.ruoyi.purchase.service;

import java.util.List;
import com.ruoyi.purchase.domain.MmsPlan;

/**
 * 材料总计划Service接口
 * 
 * @author linyuting
 * @date 2022-03-23
 */
public interface IMmsPlanService 
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
     * 批量删除材料总计划
     * 
     * @param planIds 需要删除的材料总计划主键集合
     * @return 结果
     */
    public int deleteMmsPlanByPlanIds(Long[] planIds);

    /**
     * 删除材料总计划信息
     * 
     * @param planId 材料总计划主键
     * @return 结果
     */
    public int deleteMmsPlanByPlanId(Long planId);

//    /**
//     * 查询计划详情
//     *
//     * @param planId 材料总计划主键
//     * @return 结果
//     */
//    public int selectMmsPlanDetailByPlanId( Long planId);
 }
