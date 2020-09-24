package org.crm.service.impl;

import org.crm.dao.ContactsRemarkDao;
import org.crm.entity.ContactsRemark;
import org.crm.service.ContactsRemarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ContactsRemark)表服务实现类
 *
 * @author makejava
 * @since 2020-09-14 21:36:54
 */
@Service("contactsRemarkService")
public class ContactsRemarkServiceImpl implements ContactsRemarkService {
    @Resource
    private ContactsRemarkDao contactsRemarkDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ContactsRemark queryById(String id) {
        return this.contactsRemarkDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ContactsRemark> queryAllByLimit(int offset, int limit) {
        return this.contactsRemarkDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param contactsRemark 实例对象
     * @return 实例对象
     */
    @Override
    public ContactsRemark insert(ContactsRemark contactsRemark) {
        this.contactsRemarkDao.insert(contactsRemark);
        return contactsRemark;
    }

    /**
     * 修改数据
     *
     * @param contactsRemark 实例对象
     * @return 实例对象
     */
    @Override
    public ContactsRemark update(ContactsRemark contactsRemark) {
        this.contactsRemarkDao.update(contactsRemark);
        return this.queryById(contactsRemark.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.contactsRemarkDao.deleteById(id) > 0;
    }
}