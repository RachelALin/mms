package com.ruoyi.inventory.service;

import java.util.List;
import com.ruoyi.inventory.domain.MmsBack;

/**
 * 材料退库Service接口
 * 
 * @author linyuting
 * @date 2022-04-26
 */
public interface IMmsBackService 
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
     * 批量删除材料退库
     * 
     * @param backIds 需要删除的材料退库主键集合
     * @return 结果
     */
    public int deleteMmsBackByBackIds(Long[] backIds);

    /**
     * 删除材料退库信息
     * 
     * @param backId 材料退库主键
     * @return 结果
     */
    public int deleteMmsBackByBackId(Long backId);
}
