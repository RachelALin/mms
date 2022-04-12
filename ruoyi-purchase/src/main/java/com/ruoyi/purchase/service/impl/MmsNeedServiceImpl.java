package com.ruoyi.purchase.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.purchase.domain.MmsNeedMaterial;
import com.ruoyi.purchase.mapper.MmsNeedMapper;
import com.ruoyi.purchase.domain.MmsNeed;
import com.ruoyi.purchase.service.IMmsNeedService;

/**
 * 材料需用计划Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-24
 */
@Service
public class MmsNeedServiceImpl implements IMmsNeedService 
{
    @Autowired
    private MmsNeedMapper mmsNeedMapper;

    /**
     * 查询材料需用计划
     * 
     * @param needId 材料需用计划主键
     * @return 材料需用计划
     */
    @Override
    public MmsNeed selectMmsNeedByNeedId(Long needId)
    {
        return mmsNeedMapper.selectMmsNeedByNeedId(needId);
    }

    /**
     * 查询材料需用计划列表
     * 
     * @param mmsNeed 材料需用计划
     * @return 材料需用计划
     */
    @Override
    public List<MmsNeed> selectMmsNeedList(MmsNeed mmsNeed)
    {
        return mmsNeedMapper.selectMmsNeedList(mmsNeed);
    }

    /**
     * 新增材料需用计划
     * 
     * @param mmsNeed 材料需用计划
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMmsNeed(MmsNeed mmsNeed)
    {
        mmsNeed.setCreateTime(DateUtils.getNowDate());
        int rows = mmsNeedMapper.insertMmsNeed(mmsNeed);
        insertMmsNeedMaterial(mmsNeed);
        return rows;
    }

    /**
     * 修改材料需用计划
     * 
     * @param mmsNeed 材料需用计划
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMmsNeed(MmsNeed mmsNeed)
    {
        mmsNeed.setUpdateTime(DateUtils.getNowDate());
        mmsNeedMapper.deleteMmsNeedMaterialByNeedId(mmsNeed.getNeedId());
        insertMmsNeedMaterial(mmsNeed);
        return mmsNeedMapper.updateMmsNeed(mmsNeed);
    }

    /**
     * 批量删除材料需用计划
     * 
     * @param needIds 需要删除的材料需用计划主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsNeedByNeedIds(Long[] needIds)
    {
        mmsNeedMapper.deleteMmsNeedMaterialByNeedIds(needIds);
        return mmsNeedMapper.deleteMmsNeedByNeedIds(needIds);
    }

    /**
     * 删除材料需用计划信息
     * 
     * @param needId 材料需用计划主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsNeedByNeedId(Long needId)
    {
        mmsNeedMapper.deleteMmsNeedMaterialByNeedId(needId);
        return mmsNeedMapper.deleteMmsNeedByNeedId(needId);
    }

    /**
     * 新增材料与材料需用计划信息
     * 
     * @param mmsNeed 材料需用计划对象
     */
    public void insertMmsNeedMaterial(MmsNeed mmsNeed)
    {
        List<MmsNeedMaterial> mmsNeedMaterialList = mmsNeed.getMmsNeedMaterialList();
        Long needId = mmsNeed.getNeedId();
        if (StringUtils.isNotNull(mmsNeedMaterialList))
        {
            List<MmsNeedMaterial> list = new ArrayList<MmsNeedMaterial>();
            for (MmsNeedMaterial mmsNeedMaterial : mmsNeedMaterialList)
            {
                mmsNeedMaterial.setNeedId(needId);
                list.add(mmsNeedMaterial);
            }
            if (list.size() > 0)
            {
                mmsNeedMapper.batchMmsNeedMaterial(list);
            }
        }
    }
}
