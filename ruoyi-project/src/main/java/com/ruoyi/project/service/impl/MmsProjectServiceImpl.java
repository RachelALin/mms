package com.ruoyi.project.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.MmsProjectMapper;
import com.ruoyi.project.domain.MmsProject;
import com.ruoyi.project.service.IMmsProjectService;

/**
 * 项目立项Service业务层处理
 * 
 * @author linyuting
 * @date 2022-03-16
 */
@Service
public class MmsProjectServiceImpl implements IMmsProjectService 
{
    @Autowired
    private MmsProjectMapper mmsProjectMapper;

    /**
     * 查询项目立项
     * 
     * @param proId 项目立项主键
     * @return 项目立项
     */
    @Override
    public MmsProject selectMmsProjectByProId(Long proId)
    {
        return mmsProjectMapper.selectMmsProjectByProId(proId);
    }

    /**
     * 查询项目立项列表
     * 
     * @param mmsProject 项目立项
     * @return 项目立项
     */
    @Override
    public List<MmsProject> selectMmsProjectList(MmsProject mmsProject)
    {
        return mmsProjectMapper.selectMmsProjectList(mmsProject);
    }

    /**
     * 新增项目立项
     * 
     * @param mmsProject 项目立项
     * @return 结果
     */
    @Override
    public int insertMmsProject(MmsProject mmsProject)
    {
        mmsProject.setCreateTime(DateUtils.getNowDate());
        return mmsProjectMapper.insertMmsProject(mmsProject);
    }

    /**
     * 修改项目立项
     * 
     * @param mmsProject 项目立项
     * @return 结果
     */
    @Override
    public int updateMmsProject(MmsProject mmsProject)
    {
        mmsProject.setUpdateTime(DateUtils.getNowDate());
        return mmsProjectMapper.updateMmsProject(mmsProject);
    }

    /**
     * 批量删除项目立项
     * 
     * @param proIds 需要删除的项目立项主键
     * @return 结果
     */
    @Override
    public int deleteMmsProjectByProIds(Long[] proIds)
    {
        return mmsProjectMapper.deleteMmsProjectByProIds(proIds);
    }

    /**
     * 删除项目立项信息
     * 
     * @param proId 项目立项主键
     * @return 结果
     */
    @Override
    public int deleteMmsProjectByProId(Long proId)
    {
        return mmsProjectMapper.deleteMmsProjectByProId(proId);
    }
}
