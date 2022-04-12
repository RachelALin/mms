package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.MmsProject;

/**
 * 项目立项Service接口
 * 
 * @author linyuting
 * @date 2022-03-16
 */
public interface IMmsProjectService 
{
    /**
     * 查询项目立项
     * 
     * @param proId 项目立项主键
     * @return 项目立项
     */
    public MmsProject selectMmsProjectByProId(Long proId);

    /**
     * 查询项目立项列表
     * 
     * @param mmsProject 项目立项
     * @return 项目立项集合
     */
    public List<MmsProject> selectMmsProjectList(MmsProject mmsProject);

    /**
     * 新增项目立项
     * 
     * @param mmsProject 项目立项
     * @return 结果
     */
    public int insertMmsProject(MmsProject mmsProject);

    /**
     * 修改项目立项
     * 
     * @param mmsProject 项目立项
     * @return 结果
     */
    public int updateMmsProject(MmsProject mmsProject);

    /**
     * 批量删除项目立项
     * 
     * @param proIds 需要删除的项目立项主键集合
     * @return 结果
     */
    public int deleteMmsProjectByProIds(Long[] proIds);

    /**
     * 删除项目立项信息
     * 
     * @param proId 项目立项主键
     * @return 结果
     */
    public int deleteMmsProjectByProId(Long proId);
}
