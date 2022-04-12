package com.ruoyi.inventory.mapper;

import java.util.List;
import com.ruoyi.inventory.domain.MmsDelivery;
import com.ruoyi.inventory.domain.MmsDeliveryMaterial;

/**
 * 材料出库Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-28
 */
public interface MmsDeliveryMapper 
{
    /**
     * 查询材料出库
     * 
     * @param outId 材料出库主键
     * @return 材料出库
     */
    public MmsDelivery selectMmsDeliveryByOutId(Long outId);

    /**
     * 查询材料出库列表
     * 
     * @param mmsDelivery 材料出库
     * @return 材料出库集合
     */
    public List<MmsDelivery> selectMmsDeliveryList(MmsDelivery mmsDelivery);

    /**
     * 新增材料出库
     * 
     * @param mmsDelivery 材料出库
     * @return 结果
     */
    public int insertMmsDelivery(MmsDelivery mmsDelivery);

    /**
     * 修改材料出库
     * 
     * @param mmsDelivery 材料出库
     * @return 结果
     */
    public int updateMmsDelivery(MmsDelivery mmsDelivery);

    /**
     * 删除材料出库
     * 
     * @param outId 材料出库主键
     * @return 结果
     */
    public int deleteMmsDeliveryByOutId(Long outId);

    /**
     * 批量删除材料出库
     * 
     * @param outIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsDeliveryByOutIds(Long[] outIds);

    /**
     * 批量删除材料与材料出库关联
     * 
     * @param outIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsDeliveryMaterialByOutIds(Long[] outIds);
    
    /**
     * 批量新增材料与材料出库关联
     * 
     * @param mmsDeliveryMaterialList 材料与材料出库关联列表
     * @return 结果
     */
    public int batchMmsDeliveryMaterial(List<MmsDeliveryMaterial> mmsDeliveryMaterialList);
    

    /**
     * 通过材料出库主键删除材料与材料出库关联信息
     * 
     * @param outId 材料出库ID
     * @return 结果
     */
    public int deleteMmsDeliveryMaterialByOutId(Long outId);
}
