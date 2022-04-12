package com.ruoyi.purchase.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.purchase.domain.MmsPlanMaterial;
import com.ruoyi.purchase.mapper.MmsPlanMapper;
import com.ruoyi.purchase.domain.MmsPlan;
import com.ruoyi.purchase.service.IMmsPlanService;

/**
 * 材料总计划Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-23
 */
@Service
public class MmsPlanServiceImpl implements IMmsPlanService 
{
    @Autowired
    private MmsPlanMapper mmsPlanMapper;

    /**
     * 查询材料总计划
     * 
     * @param planId 材料总计划主键
     * @return 材料总计划
     */
    @Override
    public MmsPlan selectMmsPlanByPlanId(Long planId)
    {
        return mmsPlanMapper.selectMmsPlanByPlanId(planId);
    }

    /**
     * 查询材料总计划列表
     * 
     * @param mmsPlan 材料总计划
     * @return 材料总计划
     */
    @Override
    public List<MmsPlan> selectMmsPlanList(MmsPlan mmsPlan)
    {
        return mmsPlanMapper.selectMmsPlanList(mmsPlan);
    }

    /**
     * 新增材料总计划
     * 
     * @param mmsPlan 材料总计划
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMmsPlan(MmsPlan mmsPlan)
    {
        mmsPlan.setCreateTime(DateUtils.getNowDate());
        int rows = mmsPlanMapper.insertMmsPlan(mmsPlan);
        insertMmsPlanMaterial(mmsPlan);
        return rows;
    }

    /**
     * 修改材料总计划
     * 
     * @param mmsPlan 材料总计划
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMmsPlan(MmsPlan mmsPlan)
    {
        mmsPlan.setUpdateTime(DateUtils.getNowDate());
        mmsPlanMapper.deleteMmsPlanMaterialByPlanId(mmsPlan.getPlanId());
        insertMmsPlanMaterial(mmsPlan);
        return mmsPlanMapper.updateMmsPlan(mmsPlan);
    }

    /**
     * 批量删除材料总计划
     * 
     * @param planIds 需要删除的材料总计划主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsPlanByPlanIds(Long[] planIds)
    {
        mmsPlanMapper.deleteMmsPlanMaterialByPlanIds(planIds);
        return mmsPlanMapper.deleteMmsPlanByPlanIds(planIds);
    }

    /**
     * 删除材料总计划信息
     * 
     * @param planId 材料总计划主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsPlanByPlanId(Long planId)
    {
        mmsPlanMapper.deleteMmsPlanMaterialByPlanId(planId);
        return mmsPlanMapper.deleteMmsPlanByPlanId(planId);
    }

//    @Override
//    public int selectMmsPlanDetailByPlanId(Long planId) {
//        return mmsPlanMapper.selectMmsPlanDetailByPlanId(planId);
//    }

    /**
     * 新增材料与材料总计划信息
     * 
     * @param mmsPlan 材料总计划对象
     */
    public void insertMmsPlanMaterial(MmsPlan mmsPlan)
    {
        List<MmsPlanMaterial> mmsPlanMaterialList = mmsPlan.getMmsPlanMaterialList();
        Long planId = mmsPlan.getPlanId();
        if (StringUtils.isNotNull(mmsPlanMaterialList))
        {
            List<MmsPlanMaterial> list = new ArrayList<MmsPlanMaterial>();
            for (MmsPlanMaterial mmsPlanMaterial : mmsPlanMaterialList)
            {
                mmsPlanMaterial.setPlanId(planId);
                list.add(mmsPlanMaterial);
            }
            if (list.size() > 0)
            {
                mmsPlanMapper.batchMmsPlanMaterial(list);
            }
        }
    }


}
