package org.crm.service;

import org.crm.entity.CustomerRemark;

import java.util.List;

/**
 * (CustomerRemark)表服务接口
 *
 * @author makejava
 * @since 2020-09-14 21:36:54
 */
public interface CustomerRemarkService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CustomerRemark queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<CustomerRemark> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param customerRemark 实例对象
     * @return 实例对象
     */
    CustomerRemark insert(CustomerRemark customerRemark);

    /**
     * 修改数据
     *
     * @param customerRemark 实例对象
     * @return 实例对象
     */
    CustomerRemark update(CustomerRemark customerRemark);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}