package org.crm.service;

import org.crm.entity.Tran;

import java.util.List;

/**
 * (Tran)表服务接口
 *
 * @author makejava
 * @since 2020-09-14 21:36:53
 */
public interface TranService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Tran queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Tran> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tran 实例对象
     * @return 实例对象
     */
    Tran insert(Tran tran);

    /**
     * 修改数据
     *
     * @param tran 实例对象
     * @return 实例对象
     */
    Tran update(Tran tran);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 更新指定id的线索的状态字段
     * @param id 当前登录用户的id
     * @param tran 更新用的数据
     */
    void changeState(String id, Tran tran);
}