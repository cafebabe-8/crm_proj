package org.crm.service;

import org.crm.entity.Activity;
import org.crm.entity.Page;

import java.util.List;

/**
 * (Activity)表服务接口
 *
 * @author makejava
 * @since 2020-09-05 16:21:39
 */
public interface ActivityService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Activity queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Activity> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    int insert(Activity activity, String id);

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    int update(Activity activity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<Activity> queryAll();

    int deleteByIds(String[] ids);

    void queryAllForPage(Page page, Activity activity);

    void deleteForPagination(Activity activity, String[] in, String[] notin, boolean lastState, boolean selectall);

    List<Activity> queryAllForExport(Activity activity, String[] in, String[] notin, boolean lastState, boolean selectall);

    void insertImported(List<Activity> activityList);

    List<Activity> queryReferAct(String clueId);

    List<Activity> fuzzyQuery(String keyword);
}