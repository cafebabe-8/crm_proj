package org.crm.service;

import org.crm.entity.ClueRemark;

import java.util.List;

/**
 * (ClueRemark)表服务接口
 *
 * @author makejava
 * @since 2020-09-11 19:58:43
 */
public interface ClueRemarkService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClueRemark queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ClueRemark> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param clueRemark 实例对象
     * @return 实例对象
     */
    ClueRemark insert(ClueRemark clueRemark);

    /**
     * 修改数据
     *
     * @param clueRemark 实例对象
     * @return 实例对象
     */
    ClueRemark update(ClueRemark clueRemark);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}