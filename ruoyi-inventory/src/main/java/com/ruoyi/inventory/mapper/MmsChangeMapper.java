package com.ruoyi.inventory.mapper;

import java.util.List;
import com.ruoyi.inventory.domain.MmsChange;
import com.ruoyi.inventory.domain.MmsChangeMaterial;

/**
 * 材料调拨Mapper接口
 * 
 * @author linyuting
 * @date 2022-04-26
 */
public interface MmsChangeMapper 
{
    /**
     * 查询材料调拨
     * 
     * @param chaId 材料调拨主键
     * @return 材料调拨
     */
    public MmsChange selectMmsChangeByChaId(Long chaId);

    /**
     * 查询材料调拨列表
     * 
     * @param mmsChange 材料调拨
     * @return 材料调拨集合
     */
    public List<MmsChange> selectMmsChangeList(MmsChange mmsChange);

    /**
     * 新增材料调拨
     * 
     * @param mmsChange 材料调拨
     * @return 结果
     */
    public int insertMmsChange(MmsChange mmsChange);

    /**
     * 修改材料调拨
     * 
     * @param mmsChange 材料调拨
     * @return 结果
     */
    public int updateMmsChange(MmsChange mmsChange);

    /**
     * 删除材料调拨
     * 
     * @param chaId 材料调拨主键
     * @return 结果
     */
    public int deleteMmsChangeByChaId(Long chaId);

    /**
     * 批量删除材料调拨
     * 
     * @param chaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsChangeByChaIds(Long[] chaIds);

    /**
     * 批量删除材料与材料调拨关联
     * 
     * @param chaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsChangeMaterialByChaIds(Long[] chaIds);
    
    /**
     * 批量新增材料与材料调拨关联
     * 
     * @param mmsChangeMaterialList 材料与材料调拨关联列表
     * @return 结果
     */
    public int batchMmsChangeMaterial(List<MmsChangeMaterial> mmsChangeMaterialList);
    

    /**
     * 通过材料调拨主键删除材料与材料调拨关联信息
     * 
     * @param chaId 材料调拨ID
     * @return 结果
     */
    public int deleteMmsChangeMaterialByChaId(Long chaId);
}
