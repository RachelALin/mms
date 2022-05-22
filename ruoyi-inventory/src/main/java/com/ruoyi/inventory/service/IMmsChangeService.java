package com.ruoyi.inventory.service;

import java.util.List;
import com.ruoyi.inventory.domain.MmsChange;

/**
 * 材料调拨Service接口
 * 
 * @author linyuting
 * @date 2022-04-26
 */
public interface IMmsChangeService 
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
     * 批量删除材料调拨
     * 
     * @param chaIds 需要删除的材料调拨主键集合
     * @return 结果
     */
    public int deleteMmsChangeByChaIds(Long[] chaIds);

    /**
     * 删除材料调拨信息
     * 
     * @param chaId 材料调拨主键
     * @return 结果
     */
    public int deleteMmsChangeByChaId(Long chaId);
}
