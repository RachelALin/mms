package com.ruoyi.inventory.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.inventory.domain.MmsBackMaterial;
import com.ruoyi.inventory.mapper.MmsBackMapper;
import com.ruoyi.inventory.domain.MmsBack;
import com.ruoyi.inventory.service.IMmsBackService;

/**
 * 材料退库Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-28
 */
@Service
public class MmsBackServiceImpl implements IMmsBackService 
{
    @Autowired
    private MmsBackMapper mmsBackMapper;

    /**
     * 查询材料退库
     * 
     * @param backId 材料退库主键
     * @return 材料退库
     */
    @Override
    public MmsBack selectMmsBackByBackId(Long backId)
    {
        return mmsBackMapper.selectMmsBackByBackId(backId);
    }

    /**
     * 查询材料退库列表
     * 
     * @param mmsBack 材料退库
     * @return 材料退库
     */
    @Override
    public List<MmsBack> selectMmsBackList(MmsBack mmsBack)
    {
        return mmsBackMapper.selectMmsBackList(mmsBack);
    }

    /**
     * 新增材料退库
     * 
     * @param mmsBack 材料退库
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMmsBack(MmsBack mmsBack)
    {
        mmsBack.setCreateTime(DateUtils.getNowDate());
        int rows = mmsBackMapper.insertMmsBack(mmsBack);
        insertMmsBackMaterial(mmsBack);
        return rows;
    }

    /**
     * 修改材料退库
     * 
     * @param mmsBack 材料退库
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMmsBack(MmsBack mmsBack)
    {
        mmsBack.setUpdateTime(DateUtils.getNowDate());
        mmsBackMapper.deleteMmsBackMaterialByBackId(mmsBack.getBackId());
        insertMmsBackMaterial(mmsBack);
        return mmsBackMapper.updateMmsBack(mmsBack);
    }

    /**
     * 批量删除材料退库
     * 
     * @param backIds 需要删除的材料退库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsBackByBackIds(Long[] backIds)
    {
        mmsBackMapper.deleteMmsBackMaterialByBackIds(backIds);
        return mmsBackMapper.deleteMmsBackByBackIds(backIds);
    }

    /**
     * 删除材料退库信息
     * 
     * @param backId 材料退库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsBackByBackId(Long backId)
    {
        mmsBackMapper.deleteMmsBackMaterialByBackId(backId);
        return mmsBackMapper.deleteMmsBackByBackId(backId);
    }

    /**
     * 新增材料与材料退库关联信息
     * 
     * @param mmsBack 材料退库对象
     */
    public void insertMmsBackMaterial(MmsBack mmsBack)
    {
        List<MmsBackMaterial> mmsBackMaterialList = mmsBack.getMmsBackMaterialList();
        Long backId = mmsBack.getBackId();
        if (StringUtils.isNotNull(mmsBackMaterialList))
        {
            List<MmsBackMaterial> list = new ArrayList<MmsBackMaterial>();
            for (MmsBackMaterial mmsBackMaterial : mmsBackMaterialList)
            {
                mmsBackMaterial.setBackId(backId);
                list.add(mmsBackMaterial);
            }
            if (list.size() > 0)
            {
                mmsBackMapper.batchMmsBackMaterial(list);
            }
        }
    }
}
