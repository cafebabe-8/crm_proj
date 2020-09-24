package org.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.crm.entity.ContactsActivityRelation;

import java.util.List;

/**
 * (ContactsActivityRelation)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-14 21:36:55
 */
public interface ContactsActivityRelationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ContactsActivityRelation queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ContactsActivityRelation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param contactsActivityRelation 实例对象
     * @return 对象列表
     */
    List<ContactsActivityRelation> queryAll(ContactsActivityRelation contactsActivityRelation);

    /**
     * 新增数据
     *
     * @param contactsActivityRelation 实例对象
     * @return 影响行数
     */
    int insert(ContactsActivityRelation contactsActivityRelation);

    /**
     * 修改数据
     *
     * @param contactsActivityRelation 实例对象
     * @return 影响行数
     */
    int update(ContactsActivityRelation contactsActivityRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 批量插入关系记录
     * @param list
     */
    void batchInsert(List<ContactsActivityRelation> list);
}