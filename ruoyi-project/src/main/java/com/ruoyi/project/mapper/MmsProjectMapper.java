package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.MmsProject;

/**
 * 项目立项Mapper接口
 * 
 * @author linyuting
 * @date 2022-03-16
 */
public interface MmsProjectMapper 
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
     * 删除项目立项
     * 
     * @param proId 项目立项主键
     * @return 结果
     */
    public int deleteMmsProjectByProId(Long proId);

    /**
     * 批量删除项目立项
     * 
     * @param proIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMmsProjectByProIds(Long[] proIds);
}
