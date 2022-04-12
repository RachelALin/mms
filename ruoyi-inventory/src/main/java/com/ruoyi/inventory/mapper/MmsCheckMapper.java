package com.ruoyi.inventory.mapper;

import java.util.List;
import com.ruoyi.inventory.domain.MmsCheck;
import com.ruoyi.inventory.domain.MmsCheckMaterial;

/**
 * 材料盘点Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-28
 */
public interface MmsCheckMapper 
{
    /**
     * 查询材料盘点
     * 
     * @param cheId 材料盘点主键
     * @return 材料盘点
     */
    public MmsCheck selectMmsCheckByCheId(Long cheId);

    /**
     * 查询材料盘点列表
     * 
     * @param mmsCheck 材料盘点
     * @return 材料盘点集合
     */
    public List<MmsCheck> selectMmsCheckList(MmsCheck mmsCheck);

    /**
     * 新增材料盘点
     * 
     * @param mmsCheck 材料盘点
     * @return 结果
     */
    public int insertMmsCheck(MmsCheck mmsCheck);

    /**
     * 修改材料盘点
     * 
     * @param mmsCheck 材料盘点
     * @return 结果
     */
    public int updateMmsCheck(MmsCheck mmsCheck);

    /**
     * 删除材料盘点
     * 
     * @param cheId 材料盘点主键
     * @return 结果
     */
    public int deleteMmsCheckByCheId(Long cheId);

    /**
     * 批量删除材料盘点
     * 
     * @param cheIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsCheckByCheIds(Long[] cheIds);

    /**
     * 批量删除材料与材料盘点关联
     * 
     * @param cheIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsCheckMaterialByCheIds(Long[] cheIds);
    
    /**
     * 批量新增材料与材料盘点关联
     * 
     * @param mmsCheckMaterialList 材料与材料盘点关联列表
     * @return 结果
     */
    public int batchMmsCheckMaterial(List<MmsCheckMaterial> mmsCheckMaterialList);
    

    /**
     * 通过材料盘点主键删除材料与材料盘点关联信息
     * 
     * @param cheId 材料盘点ID
     * @return 结果
     */
    public int deleteMmsCheckMaterialByCheId(Long cheId);
}
