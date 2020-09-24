package org.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.crm.entity.Clue;

import java.util.List;

/**
 * (Clue)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-11 19:58:43
 */
public interface ClueDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Clue queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Clue> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param clue 实例对象
     * @return 对象列表
     */
    List<Clue> queryAll(Clue clue);


    /**
     *
     *
     * @return 对象列表
     */
    List<Clue> queryAllWithUser();




    /**
     * 新增数据
     *
     * @param clue 实例对象
     * @return 影响行数
     */
    int insert(Clue clue);

    /**
     * 修改数据
     *
     * @param clue 实例对象
     * @return 影响行数
     */
    int update(Clue clue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}