package org.crm.service.impl;

import org.crm.dao.ContactsActivityRelationDao;
import org.crm.entity.ContactsActivityRelation;
import org.crm.service.ContactsActivityRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ContactsActivityRelation)表服务实现类
 *
 * @author makejava
 * @since 2020-09-14 21:36:55
 */
@Service("contactsActivityRelationService")
public class ContactsActivityRelationServiceImpl implements ContactsActivityRelationService {
    @Resource
    private ContactsActivityRelationDao contactsActivityRelationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ContactsActivityRelation queryById(String id) {
        return this.contactsActivityRelationDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ContactsActivityRelation> queryAllByLimit(int offset, int limit) {
        return this.contactsActivityRelationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param contactsActivityRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ContactsActivityRelation insert(ContactsActivityRelation contactsActivityRelation) {
        this.contactsActivityRelationDao.insert(contactsActivityRelation);
        return contactsActivityRelation;
    }

    /**
     * 修改数据
     *
     * @param contactsActivityRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ContactsActivityRelation update(ContactsActivityRelation contactsActivityRelation) {
        this.contactsActivityRelationDao.update(contactsActivityRelation);
        return this.queryById(contactsActivityRelation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.contactsActivityRelationDao.deleteById(id) > 0;
    }
}