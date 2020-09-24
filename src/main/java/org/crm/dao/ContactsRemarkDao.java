package org.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.crm.entity.ContactsRemark;

import java.util.List;

/**
 * (ContactsRemark)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-14 21:36:54
 */
public interface ContactsRemarkDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ContactsRemark queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ContactsRemark> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param contactsRemark 实例对象
     * @return 对象列表
     */
    List<ContactsRemark> queryAll(ContactsRemark contactsRemark);

    /**
     * 新增数据
     *
     * @param contactsRemark 实例对象
     * @return 影响行数
     */
    int insert(ContactsRemark contactsRemark);

    /**
     * 修改数据
     *
     * @param contactsRemark 实例对象
     * @return 影响行数
     */
    int update(ContactsRemark contactsRemark);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);


    /**
     * 批量插入联系人备注记录
     * @param contactsRemarkList
     */
    void batchInsert(List<ContactsRemark> contactsRemarkList);
}