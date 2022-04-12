package com.ruoyi.purchase.service;

import java.util.List;
import com.ruoyi.purchase.domain.MmsContract;

/**
 * 材料合同Service接口
 * 
 * @author linyuting
 * @date 2022-03-25
 */
public interface IMmsContractService 
{
    /**
     * 查询材料合同
     * 
     * @param conId 材料合同主键
     * @return 材料合同
     */
    public MmsContract selectMmsContractByConId(Long conId);

    /**
     * 查询材料合同列表
     * 
     * @param mmsContract 材料合同
     * @return 材料合同集合
     */
    public List<MmsContract> selectMmsContractList(MmsContract mmsContract);

    /**
     * 新增材料合同
     * 
     * @param mmsContract 材料合同
     * @return 结果
     */
    public int insertMmsContract(MmsContract mmsContract);

    /**
     * 修改材料合同
     * 
     * @param mmsContract 材料合同
     * @return 结果
     */
    public int updateMmsContract(MmsContract mmsContract);

    /**
     * 批量删除材料合同
     * 
     * @param conIds 需要删除的材料合同主键集合
     * @return 结果
     */
    public int deleteMmsContractByConIds(Long[] conIds);

    /**
     * 删除材料合同信息
     * 
     * @param conId 材料合同主键
     * @return 结果
     */
    public int deleteMmsContractByConId(Long conId);
}
