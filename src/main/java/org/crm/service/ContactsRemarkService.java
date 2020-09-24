package org.crm.service;

import org.crm.entity.ContactsRemark;

import java.util.List;

/**
 * (ContactsRemark)表服务接口
 *
 * @author makejava
 * @since 2020-09-14 21:36:54
 */
public interface ContactsRemarkService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ContactsRemark queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ContactsRemark> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param contactsRemark 实例对象
     * @return 实例对象
     */
    ContactsRemark insert(ContactsRemark contactsRemark);

    /**
     * 修改数据
     *
     * @param contactsRemark 实例对象
     * @return 实例对象
     */
    ContactsRemark update(ContactsRemark contactsRemark);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}