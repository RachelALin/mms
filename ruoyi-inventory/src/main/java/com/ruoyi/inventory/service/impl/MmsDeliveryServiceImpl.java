package com.ruoyi.inventory.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.inventory.domain.MmsDeliveryMaterial;
import com.ruoyi.inventory.mapper.MmsDeliveryMapper;
import com.ruoyi.inventory.domain.MmsDelivery;
import com.ruoyi.inventory.service.IMmsDeliveryService;

/**
 * 材料出库Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-28
 */
@Service
public class MmsDeliveryServiceImpl implements IMmsDeliveryService 
{
    @Autowired
    private MmsDeliveryMapper mmsDeliveryMapper;

    /**
     * 查询材料出库
     * 
     * @param outId 材料出库主键
     * @return 材料出库
     */
    @Override
    public MmsDelivery selectMmsDeliveryByOutId(Long outId)
    {
        return mmsDeliveryMapper.selectMmsDeliveryByOutId(outId);
    }

    /**
     * 查询材料出库列表
     * 
     * @param mmsDelivery 材料出库
     * @return 材料出库
     */
    @Override
    public List<MmsDelivery> selectMmsDeliveryList(MmsDelivery mmsDelivery)
    {
        return mmsDeliveryMapper.selectMmsDeliveryList(mmsDelivery);
    }

    /**
     * 新增材料出库
     * 
     * @param mmsDelivery 材料出库
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMmsDelivery(MmsDelivery mmsDelivery)
    {
        mmsDelivery.setCreateTime(DateUtils.getNowDate());
        int rows = mmsDeliveryMapper.insertMmsDelivery(mmsDelivery);
        insertMmsDeliveryMaterial(mmsDelivery);
        return rows;
    }

    /**
     * 修改材料出库
     * 
     * @param mmsDelivery 材料出库
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMmsDelivery(MmsDelivery mmsDelivery)
    {
        mmsDelivery.setUpdateTime(DateUtils.getNowDate());
        mmsDeliveryMapper.deleteMmsDeliveryMaterialByOutId(mmsDelivery.getOutId());
        insertMmsDeliveryMaterial(mmsDelivery);
        return mmsDeliveryMapper.updateMmsDelivery(mmsDelivery);
    }

    /**
     * 批量删除材料出库
     * 
     * @param outIds 需要删除的材料出库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsDeliveryByOutIds(Long[] outIds)
    {
        mmsDeliveryMapper.deleteMmsDeliveryMaterialByOutIds(outIds);
        return mmsDeliveryMapper.deleteMmsDeliveryByOutIds(outIds);
    }

    /**
     * 删除材料出库信息
     * 
     * @param outId 材料出库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsDeliveryByOutId(Long outId)
    {
        mmsDeliveryMapper.deleteMmsDeliveryMaterialByOutId(outId);
        return mmsDeliveryMapper.deleteMmsDeliveryByOutId(outId);
    }

    /**
     * 新增材料与材料出库关联信息
     * 
     * @param mmsDelivery 材料出库对象
     */
    public void insertMmsDeliveryMaterial(MmsDelivery mmsDelivery)
    {
        List<MmsDeliveryMaterial> mmsDeliveryMaterialList = mmsDelivery.getMmsDeliveryMaterialList();
        Long outId = mmsDelivery.getOutId();
        if (StringUtils.isNotNull(mmsDeliveryMaterialList))
        {
            List<MmsDeliveryMaterial> list = new ArrayList<MmsDeliveryMaterial>();
            for (MmsDeliveryMaterial mmsDeliveryMaterial : mmsDeliveryMaterialList)
            {
                mmsDeliveryMaterial.setOutId(outId);
                list.add(mmsDeliveryMaterial);
            }
            if (list.size() > 0)
            {
                mmsDeliveryMapper.batchMmsDeliveryMaterial(list);
            }
        }
    }
}
