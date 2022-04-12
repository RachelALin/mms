package com.ruoyi.purchase.mapper;

import java.util.List;
import com.ruoyi.purchase.domain.MmsNeed;
import com.ruoyi.purchase.domain.MmsNeedMaterial;

/**
 * 材料需用计划Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-24
 */
public interface MmsNeedMapper 
{
    /**
     * 查询材料需用计划
     * 
     * @param needId 材料需用计划主键
     * @return 材料需用计划
     */
    public MmsNeed selectMmsNeedByNeedId(Long needId);

    /**
     * 查询材料需用计划列表
     * 
     * @param mmsNeed 材料需用计划
     * @return 材料需用计划集合
     */
    public List<MmsNeed> selectMmsNeedList(MmsNeed mmsNeed);

    /**
     * 新增材料需用计划
     * 
     * @param mmsNeed 材料需用计划
     * @return 结果
     */
    public int insertMmsNeed(MmsNeed mmsNeed);

    /**
     * 修改材料需用计划
     * 
     * @param mmsNeed 材料需用计划
     * @return 结果
     */
    public int updateMmsNeed(MmsNeed mmsNeed);

    /**
     * 删除材料需用计划
     * 
     * @param needId 材料需用计划主键
     * @return 结果
     */
    public int deleteMmsNeedByNeedId(Long needId);

    /**
     * 批量删除材料需用计划
     * 
     * @param needIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsNeedByNeedIds(Long[] needIds);

    /**
     * 批量删除材料与材料需用计划
     * 
     * @param needIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsNeedMaterialByNeedIds(Long[] needIds);
    
    /**
     * 批量新增材料与材料需用计划
     * 
     * @param mmsNeedMaterialList 材料与材料需用计划列表
     * @return 结果
     */
    public int batchMmsNeedMaterial(List<MmsNeedMaterial> mmsNeedMaterialList);
    

    /**
     * 通过材料需用计划主键删除材料与材料需用计划信息
     * 
     * @param needId 材料需用计划ID
     * @return 结果
     */
    public int deleteMmsNeedMaterialByNeedId(Long needId);
}
