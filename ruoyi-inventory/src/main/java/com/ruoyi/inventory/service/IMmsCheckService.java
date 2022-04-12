package com.ruoyi.inventory.service;

import java.util.List;
import com.ruoyi.inventory.domain.MmsCheck;

/**
 * 材料盘点Service接口
 * 
 * @author linyuting
 * @date 2022-03-28
 */
public interface IMmsCheckService 
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
     * 批量删除材料盘点
     * 
     * @param cheIds 需要删除的材料盘点主键集合
     * @return 结果
     */
    public int deleteMmsCheckByCheIds(Long[] cheIds);

    /**
     * 删除材料盘点信息
     * 
     * @param cheId 材料盘点主键
     * @return 结果
     */
    public int deleteMmsCheckByCheId(Long cheId);
}
