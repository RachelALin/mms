package com.ruoyi.inventory.service;

import java.util.List;
import com.ruoyi.inventory.domain.MmsReceived;

/**
 * 材料入库Service接口
 * 
 * @author linyuting
 * @date 2022-03-26
 */
public interface IMmsReceivedService 
{
    /**
     * 查询材料入库
     * 
     * @param inId 材料入库主键
     * @return 材料入库
     */
    public MmsReceived selectMmsReceivedByInId(Long inId);

    /**
     * 查询材料入库列表
     * 
     * @param mmsReceived 材料入库
     * @return 材料入库集合
     */
    public List<MmsReceived> selectMmsReceivedList(MmsReceived mmsReceived);

    /**
     * 新增材料入库
     * 
     * @param mmsReceived 材料入库
     * @return 结果
     */
    public int insertMmsReceived(MmsReceived mmsReceived);

    /**
     * 修改材料入库
     * 
     * @param mmsReceived 材料入库
     * @return 结果
     */
    public int updateMmsReceived(MmsReceived mmsReceived);

    /**
     * 批量删除材料入库
     * 
     * @param inIds 需要删除的材料入库主键集合
     * @return 结果
     */
    public int deleteMmsReceivedByInIds(Long[] inIds);

    /**
     * 删除材料入库信息
     * 
     * @param inId 材料入库主键
     * @return 结果
     */
    public int deleteMmsReceivedByInId(Long inId);
}
