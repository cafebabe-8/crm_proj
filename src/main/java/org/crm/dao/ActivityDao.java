package org.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.crm.entity.Activity;

import java.util.List;

/**
 * (Activity)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-05 16:21:39
 */
public interface ActivityDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Activity queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Activity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param activity 实例对象
     * @return 对象列表
     */
    List<Activity> queryAll(Activity activity);

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 影响行数
     */
    int insert(Activity activity);

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 影响行数
     */
    int update(Activity activity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    int deleteByIds(String[] ids);


    // 根据筛选条件查询总的记录数
    Integer total(@Param("activity") Activity activity);

    // 根据筛选条件分页查询
    List<Activity> queryAllByLimitForPage(@Param("offset") Integer offset, @Param("limit") Integer rowsPerPage, @Param("activity") Activity activity);

    void deleteForPagination(@Param("activity") Activity activity,
                             @Param("inarray") String[] in,
                             @Param("notinarray") String[] notin,
                             @Param("lastState") boolean lastState,
                             @Param("selectall") boolean selectall);


    // 筛选查询出用于导出到excel文件的记录
    List<Activity> queryAllForExport( @Param("activity") Activity activity,
                                      @Param("inarray") String[] in,
                                      @Param("notinarray") String[] notin,
                                      @Param("lastState") boolean lastState,
                                      @Param("selectall") boolean selectall);

    // 插入excel文件中读取出来的记录
    void insertImported(@Param("actList") List<Activity> activityList);


    // 根据线索id查询对应的活动列表
    List<Activity> queryReferAct(String clueId);

    // 根据关键字模糊查询结果
    List<Activity> fuzzyQuery(String keyword);
}