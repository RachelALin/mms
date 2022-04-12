package com.ruoyi.purchase.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.purchase.mapper.MmsPlanMaterialMapper;
import com.ruoyi.purchase.domain.MmsPlanMaterial;
import com.ruoyi.purchase.service.IMmsPlanMaterialService;

/**
 * 材料与材料总计划Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-24
 */
@Service
public class MmsPlanMaterialServiceImpl implements IMmsPlanMaterialService 
{
    @Autowired
    private MmsPlanMaterialMapper mmsPlanMaterialMapper;

    /**
     * 查询材料与材料总计划
     * 
     * @param id 材料与材料总计划主键
     * @return 材料与材料总计划
     */
    @Override
    public MmsPlanMaterial selectMmsPlanMaterialById(Long id)
    {
        return mmsPlanMaterialMapper.selectMmsPlanMaterialById(id);
    }

    /**
     * 查询材料与材料总计划列表
     * 
     * @param mmsPlanMaterial 材料与材料总计划
     * @return 材料与材料总计划
     */
    @Override
    public List<MmsPlanMaterial> selectMmsPlanMaterialList(MmsPlanMaterial mmsPlanMaterial)
    {
        return mmsPlanMaterialMapper.selectMmsPlanMaterialList(mmsPlanMaterial);
    }

    /**
     * 新增材料与材料总计划
     * 
     * @param mmsPlanMaterial 材料与材料总计划
     * @return 结果
     */
    @Override
    public int insertMmsPlanMaterial(MmsPlanMaterial mmsPlanMaterial)
    {
        mmsPlanMaterial.setCreateTime(DateUtils.getNowDate());
        return mmsPlanMaterialMapper.insertMmsPlanMaterial(mmsPlanMaterial);
    }

    /**
     * 修改材料与材料总计划
     * 
     * @param mmsPlanMaterial 材料与材料总计划
     * @return 结果
     */
    @Override
    public int updateMmsPlanMaterial(MmsPlanMaterial mmsPlanMaterial)
    {
        mmsPlanMaterial.setUpdateTime(DateUtils.getNowDate());
        return mmsPlanMaterialMapper.updateMmsPlanMaterial(mmsPlanMaterial);
    }

    /**
     * 批量删除材料与材料总计划
     * 
     * @param ids 需要删除的材料与材料总计划主键
     * @return 结果
     */
    @Override
    public int deleteMmsPlanMaterialByIds(Long[] ids)
    {
        return mmsPlanMaterialMapper.deleteMmsPlanMaterialByIds(ids);
    }

    /**
     * 删除材料与材料总计划信息
     * 
     * @param id 材料与材料总计划主键
     * @return 结果
     */
    @Override
    public int deleteMmsPlanMaterialById(Long id)
    {
        return mmsPlanMaterialMapper.deleteMmsPlanMaterialById(id);
    }
}
