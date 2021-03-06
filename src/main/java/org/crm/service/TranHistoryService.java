package org.crm.service;

import org.crm.entity.TranHistory;

import java.util.List;

/**
 * (TranHistory)表服务接口
 *
 * @author makejava
 * @since 2020-09-14 21:36:53
 */
public interface TranHistoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TranHistory queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TranHistory> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tranHistory 实例对象
     * @return 实例对象
     */
    TranHistory insert(TranHistory tranHistory);

    /**
     * 修改数据
     *
     * @param tranHistory 实例对象
     * @return 实例对象
     */
    TranHistory update(TranHistory tranHistory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}