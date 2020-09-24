package org.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.crm.entity.ClueRemark;

import java.util.List;

/**
 * (ClueRemark)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-11 19:58:43
 */
public interface ClueRemarkDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClueRemark queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ClueRemark> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 根据线索id查询所有的记录
     * @param clueid
     * @return
     */
    List<ClueRemark> queryAllByClueId(String clueid);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param clueRemark 实例对象
     * @return 对象列表
     */
    List<ClueRemark> queryAll(ClueRemark clueRemark);

    /**
     * 新增数据
     *
     * @param clueRemark 实例对象
     * @return 影响行数
     */
    int insert(ClueRemark clueRemark);

    /**
     * 修改数据
     *
     * @param clueRemark 实例对象
     * @return 影响行数
     */
    int update(ClueRemark clueRemark);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 根据线索id删除记录
     * @param clueid
     */
    void deleteByClueId(String clueid);
}