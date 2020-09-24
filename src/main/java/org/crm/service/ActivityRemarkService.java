package org.crm.service;

import org.crm.entity.ActivityRemark;

import java.util.List;

/**
 * (ActivityRemark)表服务接口
 *
 * @author makejava
 * @since 2020-09-10 15:20:40
 */
public interface ActivityRemarkService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActivityRemark queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ActivityRemark> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param activityRemark 实例对象
     * @return 实例对象
     */
    void insert(ActivityRemark activityRemark);

    /**
     * 修改数据
     *
     * @param activityRemark 实例对象
     * @return 实例对象
     */
    void update(ActivityRemark activityRemark);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<ActivityRemark> queryByActivityId(String activityid);
}