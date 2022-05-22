package com.ruoyi.inventory.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.inventory.domain.MmsCheckMaterial;
import com.ruoyi.inventory.mapper.MmsCheckMapper;
import com.ruoyi.inventory.domain.MmsCheck;
import com.ruoyi.inventory.service.IMmsCheckService;

/**
 * 材料盘点Service业务层处理
 * 
 * @author linyuting
 * @date 2022-04-26
 */
@Service
public class MmsCheckServiceImpl implements IMmsCheckService 
{
    @Autowired
    private MmsCheckMapper mmsCheckMapper;

    /**
     * 查询材料盘点
     * 
     * @param cheId 材料盘点主键
     * @return 材料盘点
     */
    @Override
    public MmsCheck selectMmsCheckByCheId(Long cheId)
    {
        return mmsCheckMapper.selectMmsCheckByCheId(cheId);
    }

    /**
     * 查询材料盘点列表
     * 
     * @param mmsCheck 材料盘点
     * @return 材料盘点
     */
    @Override
    public List<MmsCheck> selectMmsCheckList(MmsCheck mmsCheck)
    {
        return mmsCheckMapper.selectMmsCheckList(mmsCheck);
    }

    /**
     * 新增材料盘点
     * 
     * @param mmsCheck 材料盘点
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMmsCheck(MmsCheck mmsCheck)
    {
        mmsCheck.setCreateTime(DateUtils.getNowDate());
        int rows = mmsCheckMapper.insertMmsCheck(mmsCheck);
        insertMmsCheckMaterial(mmsCheck);
        return rows;
    }

    /**
     * 修改材料盘点
     * 
     * @param mmsCheck 材料盘点
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMmsCheck(MmsCheck mmsCheck)
    {
        mmsCheck.setUpdateTime(DateUtils.getNowDate());
        mmsCheckMapper.deleteMmsCheckMaterialByCheId(mmsCheck.getCheId());
        insertMmsCheckMaterial(mmsCheck);
        return mmsCheckMapper.updateMmsCheck(mmsCheck);
    }

    /**
     * 批量删除材料盘点
     * 
     * @param cheIds 需要删除的材料盘点主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsCheckByCheIds(Long[] cheIds)
    {
        mmsCheckMapper.deleteMmsCheckMaterialByCheIds(cheIds);
        return mmsCheckMapper.deleteMmsCheckByCheIds(cheIds);
    }

    /**
     * 删除材料盘点信息
     * 
     * @param cheId 材料盘点主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsCheckByCheId(Long cheId)
    {
        mmsCheckMapper.deleteMmsCheckMaterialByCheId(cheId);
        return mmsCheckMapper.deleteMmsCheckByCheId(cheId);
    }

    /**
     * 新增材料与材料盘点关联信息
     * 
     * @param mmsCheck 材料盘点对象
     */
    public void insertMmsCheckMaterial(MmsCheck mmsCheck)
    {
        List<MmsCheckMaterial> mmsCheckMaterialList = mmsCheck.getMmsCheckMaterialList();
        Long cheId = mmsCheck.getCheId();
        if (StringUtils.isNotNull(mmsCheckMaterialList))
        {
            List<MmsCheckMaterial> list = new ArrayList<MmsCheckMaterial>();
            for (MmsCheckMaterial mmsCheckMaterial : mmsCheckMaterialList)
            {
                mmsCheckMaterial.setCheId(cheId);
                list.add(mmsCheckMaterial);
            }
            if (list.size() > 0)
            {
                mmsCheckMapper.batchMmsCheckMaterial(list);
            }
        }
    }
}
