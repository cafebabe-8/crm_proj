package org.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.crm.entity.TranRemark;

import java.util.List;

/**
 * (TranRemark)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-14 21:36:52
 */
public interface TranRemarkDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TranRemark queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TranRemark> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tranRemark 实例对象
     * @return 对象列表
     */
    List<TranRemark> queryAll(TranRemark tranRemark);

    /**
     * 新增数据
     *
     * @param tranRemark 实例对象
     * @return 影响行数
     */
    int insert(TranRemark tranRemark);

    /**
     * 修改数据
     *
     * @param tranRemark 实例对象
     * @return 影响行数
     */
    int update(TranRemark tranRemark);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}