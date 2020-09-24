package org.crm.service;

import org.crm.entity.TranRemark;

import java.util.List;

/**
 * (TranRemark)表服务接口
 *
 * @author makejava
 * @since 2020-09-14 21:36:52
 */
public interface TranRemarkService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TranRemark queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TranRemark> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tranRemark 实例对象
     * @return 实例对象
     */
    TranRemark insert(TranRemark tranRemark);

    /**
     * 修改数据
     *
     * @param tranRemark 实例对象
     * @return 实例对象
     */
    TranRemark update(TranRemark tranRemark);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}