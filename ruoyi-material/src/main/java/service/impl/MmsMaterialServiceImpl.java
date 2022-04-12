package com.ruoyi.material.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MmsMaterialMapper;
import com.ruoyi.material.domain.MmsMaterial;
import com.ruoyi.material.service.IMmsMaterialService;

/**
 * 材料管理Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-23
 */
@Service
public class MmsMaterialServiceImpl implements IMmsMaterialService 
{
    @Autowired
    private MmsMaterialMapper mmsMaterialMapper;

    /**
     * 查询材料管理
     * 
     * @param matId 材料管理主键
     * @return 材料管理
     */
    @Override
    public MmsMaterial selectMmsMaterialByMatId(Long matId)
    {
        return mmsMaterialMapper.selectMmsMaterialByMatId(matId);
    }

    /**
     * 查询材料管理列表
     * 
     * @param mmsMaterial 材料管理
     * @return 材料管理
     */
    @Override
    public List<MmsMaterial> selectMmsMaterialList(MmsMaterial mmsMaterial)
    {
        return mmsMaterialMapper.selectMmsMaterialList(mmsMaterial);
    }

    /**
     * 新增材料管理
     * 
     * @param mmsMaterial 材料管理
     * @return 结果
     */
    @Override
    public int insertMmsMaterial(MmsMaterial mmsMaterial)
    {
        mmsMaterial.setCreateTime(DateUtils.getNowDate());
        return mmsMaterialMapper.insertMmsMaterial(mmsMaterial);
    }

    /**
     * 修改材料管理
     * 
     * @param mmsMaterial 材料管理
     * @return 结果
     */
    @Override
    public int updateMmsMaterial(MmsMaterial mmsMaterial)
    {
        mmsMaterial.setUpdateTime(DateUtils.getNowDate());
        return mmsMaterialMapper.updateMmsMaterial(mmsMaterial);
    }

    /**
     * 批量删除材料管理
     * 
     * @param matIds 需要删除的材料管理主键
     * @return 结果
     */
    @Override
    public int deleteMmsMaterialByMatIds(Long[] matIds)
    {
        return mmsMaterialMapper.deleteMmsMaterialByMatIds(matIds);
    }

    /**
     * 删除材料管理信息
     * 
     * @param matId 材料管理主键
     * @return 结果
     */
    @Override
    public int deleteMmsMaterialByMatId(Long matId)
    {
        return mmsMaterialMapper.deleteMmsMaterialByMatId(matId);
    }
}
