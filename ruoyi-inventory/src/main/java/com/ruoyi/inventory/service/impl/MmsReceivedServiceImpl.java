package com.ruoyi.inventory.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.inventory.domain.MmsReceivedMaterial;
import com.ruoyi.inventory.mapper.MmsReceivedMapper;
import com.ruoyi.inventory.domain.MmsReceived;
import com.ruoyi.inventory.service.IMmsReceivedService;

/**
 * 材料入库Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-26
 */
@Service
public class MmsReceivedServiceImpl implements IMmsReceivedService 
{
    @Autowired
    private MmsReceivedMapper mmsReceivedMapper;

    /**
     * 查询材料入库
     * 
     * @param inId 材料入库主键
     * @return 材料入库
     */
    @Override
    public MmsReceived selectMmsReceivedByInId(Long inId)
    {
        return mmsReceivedMapper.selectMmsReceivedByInId(inId);
    }

    /**
     * 查询材料入库列表
     * 
     * @param mmsReceived 材料入库
     * @return 材料入库
     */
    @Override
    public List<MmsReceived> selectMmsReceivedList(MmsReceived mmsReceived)
    {
        return mmsReceivedMapper.selectMmsReceivedList(mmsReceived);
    }

    /**
     * 新增材料入库
     * 
     * @param mmsReceived 材料入库
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMmsReceived(MmsReceived mmsReceived)
    {
        mmsReceived.setCreateTime(DateUtils.getNowDate());
        int rows = mmsReceivedMapper.insertMmsReceived(mmsReceived);
        insertMmsReceivedMaterial(mmsReceived);
        return rows;
    }

    /**
     * 修改材料入库
     * 
     * @param mmsReceived 材料入库
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMmsReceived(MmsReceived mmsReceived)
    {
        mmsReceived.setUpdateTime(DateUtils.getNowDate());
        mmsReceivedMapper.deleteMmsReceivedMaterialByInId(mmsReceived.getInId());
        insertMmsReceivedMaterial(mmsReceived);
        return mmsReceivedMapper.updateMmsReceived(mmsReceived);
    }

    /**
     * 批量删除材料入库
     * 
     * @param inIds 需要删除的材料入库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsReceivedByInIds(Long[] inIds)
    {
        mmsReceivedMapper.deleteMmsReceivedMaterialByInIds(inIds);
        return mmsReceivedMapper.deleteMmsReceivedByInIds(inIds);
    }

    /**
     * 删除材料入库信息
     * 
     * @param inId 材料入库主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsReceivedByInId(Long inId)
    {
        mmsReceivedMapper.deleteMmsReceivedMaterialByInId(inId);
        return mmsReceivedMapper.deleteMmsReceivedByInId(inId);
    }

    /**
     * 新增材料与材料入库关联信息
     * 
     * @param mmsReceived 材料入库对象
     */
    public void insertMmsReceivedMaterial(MmsReceived mmsReceived)
    {
        List<MmsReceivedMaterial> mmsReceivedMaterialList = mmsReceived.getMmsReceivedMaterialList();
        Long inId = mmsReceived.getInId();
        if (StringUtils.isNotNull(mmsReceivedMaterialList))
        {
            List<MmsReceivedMaterial> list = new ArrayList<MmsReceivedMaterial>();
            for (MmsReceivedMaterial mmsReceivedMaterial : mmsReceivedMaterialList)
            {
                mmsReceivedMaterial.setInId(inId);
                list.add(mmsReceivedMaterial);
            }
            if (list.size() > 0)
            {
                mmsReceivedMapper.batchMmsReceivedMaterial(list);
            }
        }
    }
}
