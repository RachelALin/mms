package com.ruoyi.inventory.mapper;

import java.util.List;
import com.ruoyi.inventory.domain.MmsBack;
import com.ruoyi.inventory.domain.MmsBackMaterial;

/**
 * 材料退库Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-28
 */
public interface MmsBackMapper 
{
    /**
     * 查询材料退库
     * 
     * @param backId 材料退库主键
     * @return 材料退库
     */
    public MmsBack selectMmsBackByBackId(Long backId);

    /**
     * 查询材料退库列表
     * 
     * @param mmsBack 材料退库
     * @return 材料退库集合
     */
    public List<MmsBack> selectMmsBackList(MmsBack mmsBack);

    /**
     * 新增材料退库
     * 
     * @param mmsBack 材料退库
     * @return 结果
     */
    public int insertMmsBack(MmsBack mmsBack);

    /**
     * 修改材料退库
     * 
     * @param mmsBack 材料退库
     * @return 结果
     */
    public int updateMmsBack(MmsBack mmsBack);

    /**
     * 删除材料退库
     * 
     * @param backId 材料退库主键
     * @return 结果
     */
    public int deleteMmsBackByBackId(Long backId);

    /**
     * 批量删除材料退库
     * 
     * @param backIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsBackByBackIds(Long[] backIds);

    /**
     * 批量删除材料与材料退库关联
     * 
     * @param backIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsBackMaterialByBackIds(Long[] backIds);
    
    /**
     * 批量新增材料与材料退库关联
     * 
     * @param mmsBackMaterialList 材料与材料退库关联列表
     * @return 结果
     */
    public int batchMmsBackMaterial(List<MmsBackMaterial> mmsBackMaterialList);
    

    /**
     * 通过材料退库主键删除材料与材料退库关联信息
     * 
     * @param backId 材料退库ID
     * @return 结果
     */
    public int deleteMmsBackMaterialByBackId(Long backId);
}
