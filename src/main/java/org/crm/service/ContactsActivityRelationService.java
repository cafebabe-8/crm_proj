package org.crm.service;

import org.crm.entity.ContactsActivityRelation;

import java.util.List;

/**
 * (ContactsActivityRelation)表服务接口
 *
 * @author makejava
 * @since 2020-09-14 21:36:55
 */
public interface ContactsActivityRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ContactsActivityRelation queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ContactsActivityRelation> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param contactsActivityRelation 实例对象
     * @return 实例对象
     */
    ContactsActivityRelation insert(ContactsActivityRelation contactsActivityRelation);

    /**
     * 修改数据
     *
     * @param contactsActivityRelation 实例对象
     * @return 实例对象
     */
    ContactsActivityRelation update(ContactsActivityRelation contactsActivityRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}