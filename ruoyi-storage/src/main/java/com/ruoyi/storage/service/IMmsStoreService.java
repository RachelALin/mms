package com.ruoyi.storage.service;

import java.util.List;
import com.ruoyi.storage.domain.MmsStore;

/**
 * 仓库管理Service接口
 * 
 * @author linyuting
 * @date 2022-03-26
 */
public interface IMmsStoreService 
{
    /**
     * 查询仓库管理
     * 
     * @param storeId 仓库管理主键
     * @return 仓库管理
     */
    public MmsStore selectMmsStoreByStoreId(Long storeId);

    /**
     * 查询仓库管理列表
     * 
     * @param mmsStore 仓库管理
     * @return 仓库管理集合
     */
    public List<MmsStore> selectMmsStoreList(MmsStore mmsStore);

    /**
     * 新增仓库管理
     * 
     * @param mmsStore 仓库管理
     * @return 结果
     */
    public int insertMmsStore(MmsStore mmsStore);

    /**
     * 修改仓库管理
     * 
     * @param mmsStore 仓库管理
     * @return 结果
     */
    public int updateMmsStore(MmsStore mmsStore);

    /**
     * 批量删除仓库管理
     * 
     * @param storeIds 需要删除的仓库管理主键集合
     * @return 结果
     */
    public int deleteMmsStoreByStoreIds(Long[] storeIds);

    /**
     * 删除仓库管理信息
     * 
     * @param storeId 仓库管理主键
     * @return 结果
     */
    public int deleteMmsStoreByStoreId(Long storeId);

    /**
     * 根据proId查询仓库管理信息
     *
     * @param proId 项目主键
     * @return 结果
     */
    public List<MmsStore> selectMmsStoreByProId(Long proId);


}
