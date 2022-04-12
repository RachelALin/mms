package com.ruoyi.purchase.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.purchase.domain.MmsContractMaterial;
import com.ruoyi.purchase.mapper.MmsContractMapper;
import com.ruoyi.purchase.domain.MmsContract;
import com.ruoyi.purchase.service.IMmsContractService;

/**
 * 材料合同Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-25
 */
@Service
public class MmsContractServiceImpl implements IMmsContractService 
{
    @Autowired
    private MmsContractMapper mmsContractMapper;

    /**
     * 查询材料合同
     * 
     * @param conId 材料合同主键
     * @return 材料合同
     */
    @Override
    public MmsContract selectMmsContractByConId(Long conId)
    {
        return mmsContractMapper.selectMmsContractByConId(conId);
    }

    /**
     * 查询材料合同列表
     * 
     * @param mmsContract 材料合同
     * @return 材料合同
     */
    @Override
    public List<MmsContract> selectMmsContractList(MmsContract mmsContract)
    {
        return mmsContractMapper.selectMmsContractList(mmsContract);
    }

    /**
     * 新增材料合同
     * 
     * @param mmsContract 材料合同
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMmsContract(MmsContract mmsContract)
    {
        mmsContract.setCreateTime(DateUtils.getNowDate());
        int rows = mmsContractMapper.insertMmsContract(mmsContract);
        insertMmsContractMaterial(mmsContract);
        return rows;
    }

    /**
     * 修改材料合同
     * 
     * @param mmsContract 材料合同
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMmsContract(MmsContract mmsContract)
    {
        mmsContract.setUpdateTime(DateUtils.getNowDate());
        mmsContractMapper.deleteMmsContractMaterialByConId(mmsContract.getConId());
        insertMmsContractMaterial(mmsContract);
        return mmsContractMapper.updateMmsContract(mmsContract);
    }

    /**
     * 批量删除材料合同
     * 
     * @param conIds 需要删除的材料合同主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsContractByConIds(Long[] conIds)
    {
        mmsContractMapper.deleteMmsContractMaterialByConIds(conIds);
        return mmsContractMapper.deleteMmsContractByConIds(conIds);
    }

    /**
     * 删除材料合同信息
     * 
     * @param conId 材料合同主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsContractByConId(Long conId)
    {
        mmsContractMapper.deleteMmsContractMaterialByConId(conId);
        return mmsContractMapper.deleteMmsContractByConId(conId);
    }

    /**
     * 新增材料与合同关联信息
     * 
     * @param mmsContract 材料合同对象
     */
    public void insertMmsContractMaterial(MmsContract mmsContract)
    {
        List<MmsContractMaterial> mmsContractMaterialList = mmsContract.getMmsContractMaterialList();
        Long conId = mmsContract.getConId();
        if (StringUtils.isNotNull(mmsContractMaterialList))
        {
            List<MmsContractMaterial> list = new ArrayList<MmsContractMaterial>();
            for (MmsContractMaterial mmsContractMaterial : mmsContractMaterialList)
            {
                mmsContractMaterial.setConId(conId);
                list.add(mmsContractMaterial);
            }
            if (list.size() > 0)
            {
                mmsContractMapper.batchMmsContractMaterial(list);
            }
        }
    }
}
