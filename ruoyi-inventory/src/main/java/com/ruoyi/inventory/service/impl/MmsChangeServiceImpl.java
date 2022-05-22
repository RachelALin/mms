package com.ruoyi.inventory.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.inventory.domain.MmsChangeMaterial;
import com.ruoyi.inventory.mapper.MmsChangeMapper;
import com.ruoyi.inventory.domain.MmsChange;
import com.ruoyi.inventory.service.IMmsChangeService;

/**
 * 材料调拨Service业务层处理
 * 
 * @author linyuting
 * @date 2022-04-26
 */
@Service
public class MmsChangeServiceImpl implements IMmsChangeService 
{
    @Autowired
    private MmsChangeMapper mmsChangeMapper;

    /**
     * 查询材料调拨
     * 
     * @param chaId 材料调拨主键
     * @return 材料调拨
     */
    @Override
    public MmsChange selectMmsChangeByChaId(Long chaId)
    {
        return mmsChangeMapper.selectMmsChangeByChaId(chaId);
    }

    /**
     * 查询材料调拨列表
     * 
     * @param mmsChange 材料调拨
     * @return 材料调拨
     */
    @Override
    public List<MmsChange> selectMmsChangeList(MmsChange mmsChange)
    {
        return mmsChangeMapper.selectMmsChangeList(mmsChange);
    }

    /**
     * 新增材料调拨
     * 
     * @param mmsChange 材料调拨
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMmsChange(MmsChange mmsChange)
    {
        mmsChange.setCreateTime(DateUtils.getNowDate());
        int rows = mmsChangeMapper.insertMmsChange(mmsChange);
        insertMmsChangeMaterial(mmsChange);
        return rows;
    }

    /**
     * 修改材料调拨
     * 
     * @param mmsChange 材料调拨
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMmsChange(MmsChange mmsChange)
    {
        mmsChange.setUpdateTime(DateUtils.getNowDate());
        mmsChangeMapper.deleteMmsChangeMaterialByChaId(mmsChange.getChaId());
        insertMmsChangeMaterial(mmsChange);
        return mmsChangeMapper.updateMmsChange(mmsChange);
    }

    /**
     * 批量删除材料调拨
     * 
     * @param chaIds 需要删除的材料调拨主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsChangeByChaIds(Long[] chaIds)
    {
        mmsChangeMapper.deleteMmsChangeMaterialByChaIds(chaIds);
        return mmsChangeMapper.deleteMmsChangeByChaIds(chaIds);
    }

    /**
     * 删除材料调拨信息
     * 
     * @param chaId 材料调拨主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsChangeByChaId(Long chaId)
    {
        mmsChangeMapper.deleteMmsChangeMaterialByChaId(chaId);
        return mmsChangeMapper.deleteMmsChangeByChaId(chaId);
    }

    /**
     * 新增材料与材料调拨关联信息
     * 
     * @param mmsChange 材料调拨对象
     */
    public void insertMmsChangeMaterial(MmsChange mmsChange)
    {
        List<MmsChangeMaterial> mmsChangeMaterialList = mmsChange.getMmsChangeMaterialList();
        Long chaId = mmsChange.getChaId();
        if (StringUtils.isNotNull(mmsChangeMaterialList))
        {
            List<MmsChangeMaterial> list = new ArrayList<MmsChangeMaterial>();
            for (MmsChangeMaterial mmsChangeMaterial : mmsChangeMaterialList)
            {
                mmsChangeMaterial.setChaId(chaId);
                list.add(mmsChangeMaterial);
            }
            if (list.size() > 0)
            {
                mmsChangeMapper.batchMmsChangeMaterial(list);
            }
        }
    }
}
