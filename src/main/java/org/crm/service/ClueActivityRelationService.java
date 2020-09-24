package org.crm.service;

import org.crm.entity.ClueActivityRelation;

import java.util.List;

/**
 * (ClueActivityRelation)表服务接口
 *
 * @author makejava
 * @since 2020-09-11 19:58:43
 */
public interface ClueActivityRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClueActivityRelation queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ClueActivityRelation> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param clueActivityRelation 实例对象
     * @return 实例对象
     */
    ClueActivityRelation insert(ClueActivityRelation clueActivityRelation);

    /**
     * 修改数据
     *
     * @param clueActivityRelation 实例对象
     * @return 实例对象
     */
    ClueActivityRelation update(ClueActivityRelation clueActivityRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    void deleteOneRelation(String clueId, String actId);

    void insertRelation(String clueId, String[] actIds);
}