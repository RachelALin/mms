package com.ruoyi.inventory.mapper;

import java.util.List;
import com.ruoyi.inventory.domain.MmsReceived;
import com.ruoyi.inventory.domain.MmsReceivedMaterial;

/**
 * 材料入库Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-26
 */
public interface MmsReceivedMapper 
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
     * 删除材料入库
     * 
     * @param inId 材料入库主键
     * @return 结果
     */
    public int deleteMmsReceivedByInId(Long inId);

    /**
     * 批量删除材料入库
     * 
     * @param inIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsReceivedByInIds(Long[] inIds);

    /**
     * 批量删除材料与材料入库关联
     * 
     * @param inIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsReceivedMaterialByInIds(Long[] inIds);
    
    /**
     * 批量新增材料与材料入库关联
     * 
     * @param mmsReceivedMaterialList 材料与材料入库关联列表
     * @return 结果
     */
    public int batchMmsReceivedMaterial(List<MmsReceivedMaterial> mmsReceivedMaterialList);
    

    /**
     * 通过材料入库主键删除材料与材料入库关联信息
     * 
     * @param inId 材料入库ID
     * @return 结果
     */
    public int deleteMmsReceivedMaterialByInId(Long inId);
}
