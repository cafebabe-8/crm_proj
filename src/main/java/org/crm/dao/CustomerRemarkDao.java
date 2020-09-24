package org.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.crm.entity.CustomerRemark;

import java.util.List;

/**
 * (CustomerRemark)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-14 21:36:53
 */
public interface CustomerRemarkDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CustomerRemark queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<CustomerRemark> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param customerRemark 实例对象
     * @return 对象列表
     */
    List<CustomerRemark> queryAll(CustomerRemark customerRemark);

    /**
     * 新增数据
     *
     * @param customerRemark 实例对象
     * @return 影响行数
     */
    int insert(CustomerRemark customerRemark);

    /**
     * 修改数据
     *
     * @param customerRemark 实例对象
     * @return 影响行数
     */
    int update(CustomerRemark customerRemark);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);


    /**
     * 批量插入记录
     * @param customerRemarkList
     */
    void batchInsert(List<CustomerRemark> customerRemarkList);
}