package org.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.crm.entity.Clue;
import org.crm.entity.ClueActivityRelation;

import java.util.List;

/**
 * (ClueActivityRelation)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-11 19:58:43
 */
public interface ClueActivityRelationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClueActivityRelation queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ClueActivityRelation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 根据线索id查询所有记录
     * @param clueid
     * @return
     */
    List<ClueActivityRelation> queryAllByClueId(String clueid);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param clueActivityRelation 实例对象
     * @return 对象列表
     */
    List<ClueActivityRelation> queryAll(ClueActivityRelation clueActivityRelation);

    /**
     * 新增数据
     *
     * @param clueActivityRelation 实例对象
     * @return 影响行数
     */
    int insert(ClueActivityRelation clueActivityRelation);

    /**
     * 修改数据
     *
     * @param clueActivityRelation 实例对象
     * @return 影响行数
     */
    int update(ClueActivityRelation clueActivityRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 根据线索的id和活动的id删除指定行
     * @param clueId
     * @param actId
     */
    void deleteOneRelation(@Param("clueId") String clueId,
                           @Param("actId") String actId);


    /**
     * 根据线索的id所有的关系记录
     * @param clueId
     */
    void deleteByClueId(String clueId);

    /**
     * 插入list集合中的所有记录
     * @param relationList
     */
    void batchInsert(List<ClueActivityRelation> relationList);
}