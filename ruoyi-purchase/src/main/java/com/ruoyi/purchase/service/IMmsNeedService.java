package com.ruoyi.purchase.service;

import java.util.List;
import com.ruoyi.purchase.domain.MmsNeed;

/**
 * 材料需用计划Service接口
 * 
 * @author linyuting
 * @date 2022-03-24
 */
public interface IMmsNeedService 
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
     * 批量删除材料需用计划
     * 
     * @param needIds 需要删除的材料需用计划主键集合
     * @return 结果
     */
    public int deleteMmsNeedByNeedIds(Long[] needIds);

    /**
     * 删除材料需用计划信息
     * 
     * @param needId 材料需用计划主键
     * @return 结果
     */
    public int deleteMmsNeedByNeedId(Long needId);
}
