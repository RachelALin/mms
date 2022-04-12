package com.ruoyi.purchase.mapper;

import java.util.List;
import com.ruoyi.purchase.domain.MmsContract;
import com.ruoyi.purchase.domain.MmsContractMaterial;

/**
 * 材料合同Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-25
 */
public interface MmsContractMapper 
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
     * 删除材料合同
     * 
     * @param conId 材料合同主键
     * @return 结果
     */
    public int deleteMmsContractByConId(Long conId);

    /**
     * 批量删除材料合同
     * 
     * @param conIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsContractByConIds(Long[] conIds);

    /**
     * 批量删除材料与合同关联
     * 
     * @param conIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsContractMaterialByConIds(Long[] conIds);
    
    /**
     * 批量新增材料与合同关联
     * 
     * @param mmsContractMaterialList 材料与合同关联列表
     * @return 结果
     */
    public int batchMmsContractMaterial(List<MmsContractMaterial> mmsContractMaterialList);
    

    /**
     * 通过材料合同主键删除材料与合同关联信息
     * 
     * @param conId 材料合同ID
     * @return 结果
     */
    public int deleteMmsContractMaterialByConId(Long conId);
}
