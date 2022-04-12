package com.ruoyi.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.MmsProjectTypeMapper;
import com.ruoyi.project.domain.MmsProjectType;
import com.ruoyi.project.service.IMmsProjectTypeService;

/**
 * 项目类型Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-16
 */
@Service
public class MmsProjectTypeServiceImpl implements IMmsProjectTypeService 
{
    @Autowired
    private MmsProjectTypeMapper mmsProjectTypeMapper;

    /**
     * 查询项目类型
     * 
     * @param id 项目类型主键
     * @return 项目类型
     */
    @Override
    public MmsProjectType selectMmsProjectTypeById(Long id)
    {
        return mmsProjectTypeMapper.selectMmsProjectTypeById(id);
    }

    /**
     * 查询项目类型列表
     * 
     * @param mmsProjectType 项目类型
     * @return 项目类型
     */
    @Override
    public List<MmsProjectType> selectMmsProjectTypeList(MmsProjectType mmsProjectType)
    {
        return mmsProjectTypeMapper.selectMmsProjectTypeList(mmsProjectType);
    }

    /**
     * 新增项目类型
     * 
     * @param mmsProjectType 项目类型
     * @return 结果
     */
    @Override
    public int insertMmsProjectType(MmsProjectType mmsProjectType)
    {
        return mmsProjectTypeMapper.insertMmsProjectType(mmsProjectType);
    }

    /**
     * 修改项目类型
     * 
     * @param mmsProjectType 项目类型
     * @return 结果
     */
    @Override
    public int updateMmsProjectType(MmsProjectType mmsProjectType)
    {
        return mmsProjectTypeMapper.updateMmsProjectType(mmsProjectType);
    }

    /**
     * 批量删除项目类型
     * 
     * @param ids 需要删除的项目类型主键
     * @return 结果
     */
    @Override
    public int deleteMmsProjectTypeByIds(Long[] ids)
    {
        return mmsProjectTypeMapper.deleteMmsProjectTypeByIds(ids);
    }

    /**
     * 删除项目类型信息
     * 
     * @param id 项目类型主键
     * @return 结果
     */
    @Override
    public int deleteMmsProjectTypeById(Long id)
    {
        return mmsProjectTypeMapper.deleteMmsProjectTypeById(id);
    }
}
