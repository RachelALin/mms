package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.MmsProjectType;

/**
 * 项目类型Service接口
 * 
 * @author linyuting
 * @date 2022-03-16
 */
public interface IMmsProjectTypeService 
{
    /**
     * 查询项目类型
     * 
     * @param id 项目类型主键
     * @return 项目类型
     */
    public MmsProjectType selectMmsProjectTypeById(Long id);

    /**
     * 查询项目类型列表
     * 
     * @param mmsProjectType 项目类型
     * @return 项目类型集合
     */
    public List<MmsProjectType> selectMmsProjectTypeList(MmsProjectType mmsProjectType);

    /**
     * 新增项目类型
     * 
     * @param mmsProjectType 项目类型
     * @return 结果
     */
    public int insertMmsProjectType(MmsProjectType mmsProjectType);

    /**
     * 修改项目类型
     * 
     * @param mmsProjectType 项目类型
     * @return 结果
     */
    public int updateMmsProjectType(MmsProjectType mmsProjectType);

    /**
     * 批量删除项目类型
     * 
     * @param ids 需要删除的项目类型主键集合
     * @return 结果
     */
    public int deleteMmsProjectTypeByIds(Long[] ids);

    /**
     * 删除项目类型信息
     * 
     * @param id 项目类型主键
     * @return 结果
     */
    public int deleteMmsProjectTypeById(Long id);
}
