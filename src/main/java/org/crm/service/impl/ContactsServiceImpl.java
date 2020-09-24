package org.crm.service.impl;

import org.crm.dao.ContactsDao;
import org.crm.entity.Contacts;
import org.crm.service.ContactsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Contacts)表服务实现类
 *
 * @author makejava
 * @since 2020-09-14 21:36:55
 */
@Service("contactsService")
public class ContactsServiceImpl implements ContactsService {
    @Resource
    private ContactsDao contactsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Contacts queryById(String id) {
        return this.contactsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Contacts> queryAllByLimit(int offset, int limit) {
        return this.contactsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param contacts 实例对象
     * @return 实例对象
     */
    @Override
    public Contacts insert(Contacts contacts) {
        this.contactsDao.insert(contacts);
        return contacts;
    }

    /**
     * 修改数据
     *
     * @param contacts 实例对象
     * @return 实例对象
     */
    @Override
    public Contacts update(Contacts contacts) {
        this.contactsDao.update(contacts);
        return this.queryById(contacts.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.contactsDao.deleteById(id) > 0;
    }
}