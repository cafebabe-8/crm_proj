package org.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.crm.entity.Tran;

import java.util.List;

/**
 * (Tran)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-14 21:36:53
 */
public interface TranDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Tran queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Tran> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tran 实例对象
     * @return 对象列表
     */
    List<Tran> queryAll(Tran tran);

    /**
     * 新增数据
     *
     * @param tran 实例对象
     * @return 影响行数
     */
    int insert(Tran tran);

    /**
     * 修改数据
     *
     * @param tran 实例对象
     * @return 影响行数
     */
    int update(Tran tran);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}