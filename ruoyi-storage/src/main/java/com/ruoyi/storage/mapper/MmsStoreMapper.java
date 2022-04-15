package com.ruoyi.storage.mapper;

import java.util.List;
import com.ruoyi.storage.domain.MmsStore;
import com.ruoyi.storage.domain.MmsStoreMaterial;

/**
 * 仓库管理Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-26
 */
public interface MmsStoreMapper 
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
     * 删除仓库管理
     * 
     * @param storeId 仓库管理主键
     * @return 结果
     */
    public int deleteMmsStoreByStoreId(Long storeId);

    /**
     * 批量删除仓库管理
     * 
     * @param storeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsStoreByStoreIds(Long[] storeIds);

    /**
     * 批量删除材料与仓库关联
     * 
     * @param storeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsStoreMaterialByStoreIds(Long[] storeIds);
    
    /**
     * 批量新增材料与仓库关联
     * 
     * @param mmsStoreMaterialList 材料与仓库关联列表
     * @return 结果
     */
    public int batchMmsStoreMaterial(List<MmsStoreMaterial> mmsStoreMaterialList);
    

    /**
     * 通过仓库管理主键删除材料与仓库关联信息
     * 
     * @param storeId 仓库管理ID
     * @return 结果
     */
    public int deleteMmsStoreMaterialByStoreId(Long storeId);
    /**
     * 根据proId查询仓库管理
     *
     * @param proId 项目主键
     * @return 仓库管理
     */
    public List<MmsStore> selectMmsStoreByProId(Long proId);

    /**
     * 材料入库时修改仓库管理
     *
     * @param mmsStore 仓库管理
     * @return 结果
     */
    public int updateMmsStoreReceived(MmsStore mmsStore);


}
