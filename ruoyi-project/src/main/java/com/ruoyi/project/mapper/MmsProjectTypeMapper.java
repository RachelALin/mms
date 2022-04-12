package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.MmsProjectType;

/**
 * 项目类型Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-16
 */
public interface MmsProjectTypeMapper 
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
     * 删除项目类型
     * 
     * @param id 项目类型主键
     * @return 结果
     */
    public int deleteMmsProjectTypeById(Long id);

    /**
     * 批量删除项目类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsProjectTypeByIds(Long[] ids);
}
