package org.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.crm.entity.ActivityRemark;

import java.util.List;

/**
 * (ActivityRemark)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-10 15:20:40
 */
public interface ActivityRemarkDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActivityRemark queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ActivityRemark> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param activityRemark 实例对象
     * @return 对象列表
     */
    List<ActivityRemark> queryAll(ActivityRemark activityRemark);

    /**
     * 新增数据
     *
     * @param activityRemark 实例对象
     * @return 影响行数
     */
    int insert(ActivityRemark activityRemark);

    /**
     * 修改数据
     *
     * @param activityRemark 实例对象
     * @return 影响行数
     */
    int update(ActivityRemark activityRemark);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);


    /**
     * 通过活动的id来查询对应的所有的备注
     * @param activityid 活动id
     * @return 对应的所有记录组成的列表
     */
    List<ActivityRemark> queryByActivityId(String activityid);
}