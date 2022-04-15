package com.ruoyi.storage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.storage.domain.MmsStoreMaterial;
import com.ruoyi.storage.mapper.MmsStoreMapper;
import com.ruoyi.storage.domain.MmsStore;
import com.ruoyi.storage.service.IMmsStoreService;

/**
 * 仓库管理Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-26
 */
@Service
public class MmsStoreServiceImpl implements IMmsStoreService 
{
    @Autowired
    private MmsStoreMapper mmsStoreMapper;

    /**
     * 查询仓库管理
     * 
     * @param storeId 仓库管理主键
     * @return 仓库管理
     */
    @Override
    public MmsStore selectMmsStoreByStoreId(Long storeId)
    {
        return mmsStoreMapper.selectMmsStoreByStoreId(storeId);
    }

    /**
     * 查询仓库管理列表
     * 
     * @param mmsStore 仓库管理
     * @return 仓库管理
     */
    @Override
    public List<MmsStore> selectMmsStoreList(MmsStore mmsStore)
    {
        return mmsStoreMapper.selectMmsStoreList(mmsStore);
    }

    /**
     * 新增仓库管理
     * 
     * @param mmsStore 仓库管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMmsStore(MmsStore mmsStore)
    {
        mmsStore.setCreateTime(DateUtils.getNowDate());
        int rows = mmsStoreMapper.insertMmsStore(mmsStore);
        insertMmsStoreMaterial(mmsStore);
        return rows;
    }

    /**
     * 修改仓库管理
     * 
     * @param mmsStore 仓库管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMmsStore(MmsStore mmsStore)
    {
        mmsStore.setUpdateTime(DateUtils.getNowDate());
        mmsStoreMapper.deleteMmsStoreMaterialByStoreId(mmsStore.getStoreId());
        insertMmsStoreMaterial(mmsStore);
        return mmsStoreMapper.updateMmsStore(mmsStore);
    }

    /**
     * 批量删除仓库管理
     * 
     * @param storeIds 需要删除的仓库管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsStoreByStoreIds(Long[] storeIds)
    {
        mmsStoreMapper.deleteMmsStoreMaterialByStoreIds(storeIds);
        return mmsStoreMapper.deleteMmsStoreByStoreIds(storeIds);
    }

    /**
     * 删除仓库管理信息
     * 
     * @param storeId 仓库管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMmsStoreByStoreId(Long storeId)
    {
        mmsStoreMapper.deleteMmsStoreMaterialByStoreId(storeId);
        return mmsStoreMapper.deleteMmsStoreByStoreId(storeId);
    }

    @Override
    public List<MmsStore> selectMmsStoreByProId(Long proId) {

        return mmsStoreMapper.selectMmsStoreByProId(proId);
    }



    /**
     * 新增材料与仓库关联信息
     * 
     * @param mmsStore 仓库管理对象
     */
    public void insertMmsStoreMaterial(MmsStore mmsStore)
    {
        List<MmsStoreMaterial> mmsStoreMaterialList = mmsStore.getMmsStoreMaterialList();
        Long storeId = mmsStore.getStoreId();
        if (StringUtils.isNotNull(mmsStoreMaterialList))
        {
            List<MmsStoreMaterial> list = new ArrayList<MmsStoreMaterial>();
            for (MmsStoreMaterial mmsStoreMaterial : mmsStoreMaterialList)
            {
                mmsStoreMaterial.setStoreId(storeId);
                list.add(mmsStoreMaterial);
            }
            if (list.size() > 0)
            {
                mmsStoreMapper.batchMmsStoreMaterial(list);
            }
        }
    }
}
