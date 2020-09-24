package org.crm.service;

import org.crm.entity.Contacts;

import java.util.List;

/**
 * (Contacts)表服务接口
 *
 * @author makejava
 * @since 2020-09-14 21:36:55
 */
public interface ContactsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Contacts queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Contacts> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param contacts 实例对象
     * @return 实例对象
     */
    Contacts insert(Contacts contacts);

    /**
     * 修改数据
     *
     * @param contacts 实例对象
     * @return 实例对象
     */
    Contacts update(Contacts contacts);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}