package com.ruoyi.inventory.service;

import java.util.List;
import com.ruoyi.inventory.domain.MmsDelivery;

/**
 * 材料出库Service接口
 * 
 * @author linyuting
 * @date 2022-03-28
 */
public interface IMmsDeliveryService 
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
     * 批量删除材料出库
     * 
     * @param outIds 需要删除的材料出库主键集合
     * @return 结果
     */
    public int deleteMmsDeliveryByOutIds(Long[] outIds);

    /**
     * 删除材料出库信息
     * 
     * @param outId 材料出库主键
     * @return 结果
     */
    public int deleteMmsDeliveryByOutId(Long outId);
}
