package org.crm.service.impl;

import org.crm.dao.*;
import org.crm.entity.*;
import org.crm.service.ClueService;
import org.crm.service.ContactsActivityRelationService;
import org.crm.utils.DateTimeUtil;
import org.crm.utils.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Clue)表服务实现类
 *
 * @author makejava
 * @since 2020-09-11 19:58:43
 */
@Service("clueService")
public class ClueServiceImpl implements ClueService {
    @Resource
    private ClueDao clueDao;

    @Resource
    private ClueRemarkDao clueRemarkDao;

    @Resource
    private ClueActivityRelationDao clueActivityRelationDao;

    @Resource
    private CustomerDao customerDao;

    @Resource
    private CustomerRemarkDao customerRemarkDao;

    @Resource
    private ContactsDao contactsDao;

    @Resource
    private ContactsRemarkDao contactsRemarkDao;

    @Resource
    private ContactsActivityRelationDao contactsActivityRelationDao;

    @Resource
    private TranDao tranDao;

    @Resource
    private TranHistoryDao tranHistoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Clue queryById(String id) {
        return this.clueDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Clue> queryAllByLimit(int offset, int limit) {
        return this.clueDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param clue 实例对象
     * @return 实例对象
     */
    @Override
    public Clue insert(Clue clue) {
        this.clueDao.insert(clue);
        return clue;
    }

    /**
     * 修改数据
     *
     * @param clue 实例对象
     * @return 实例对象
     */
    @Override
    public Clue update(Clue clue) {
        this.clueDao.update(clue);
        return this.queryById(clue.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.clueDao.deleteById(id) > 0;
    }

    @Override
    public void insertOrUpdate(Clue clue, String userId) {
        if ("".equals(clue.getId())) {
            // 插入新的记录
            clue.setId(UUIDUtil.getUUID());
            clue.setCreatetime(DateTimeUtil.getSysTime());
            clue.setCreateby(userId);
            clueDao.insert(clue);
        } else {
            // 更新已有的记录
            clue.setEditby(userId);
            clue.setCreatetime(DateTimeUtil.getSysTime());
            clueDao.update(clue);
        }
    }

    @Override
    public List<Clue> queryAllWithUser() {
        return clueDao.queryAllWithUser();
    }


    @Override
    public void convert(String userid, Tran tran, String clueid, Boolean tranflag) {
        // 先把对应的线索查询出来
        Clue clue = clueDao.queryById(clueid);
        // 新增客户（公司） 由于这个公司可能已经成为客户 故需做判断
        String company = clue.getCompany();
        Customer customer = customerDao.queryByName(company);
        if (customer == null) {
            // 组装customer
            customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setOwner(clue.getOwner());
            customer.setName(company);
            customer.setWebsite(clue.getWebsite());
            customer.setPhone(clue.getPhone());
            customer.setCreateby(userid);
            customer.setCreatetime(DateTimeUtil.getSysTime());
            customer.setContactsummary(clue.getContactsummary());
            customer.setNextcontacttime(clue.getNextcontacttime());
            customer.setDescription(clue.getDescription());
            customer.setAddress(clue.getAddress());
            customerDao.insert(customer);
        }
        // 新增客户备注
        List<ClueRemark> clueRemarkList = clue.getClueRemarkList();
        List<CustomerRemark> customerRemarkList = new ArrayList<>();
        for (ClueRemark c : clueRemarkList) {
            CustomerRemark customerRemark = new CustomerRemark();
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setCreateby(userid);
            customerRemark.setCreatetime(DateTimeUtil.getSysTime());
            customerRemark.setCustomerid(customer.getId());
            customerRemark.setNotecontent(c.getNotecontent());
            customerRemarkList.add(customerRemark);

        }
        customerRemarkDao.batchInsert(customerRemarkList);

        // 新增contacts contactsremark ..relation三张表中的记录
        // 联系人
        Contacts contacts = new Contacts();
        contacts.setId(UUIDUtil.getUUID());
        contacts.setAddress(clue.getAddress());
        contacts.setAppellation(clue.getAppellation());
        contacts.setContactsummary(clue.getContactsummary());
        contacts.setCreateby(userid);
        contacts.setCreatetime(DateTimeUtil.getSysTime());
        contacts.setCustomerid(customer.getId());
        contacts.setDescription(clue.getDescription());
        contacts.setEmail(clue.getEmail());
        contacts.setFullname(clue.getFullname());
        contacts.setJob(clue.getJob());
        contacts.setMphone(clue.getMphone());
        contacts.setNextcontacttime(clue.getNextcontacttime());
        contacts.setOwner(clue.getOwner());
        contacts.setSource(clue.getSource());
        contactsDao.insert(contacts);

        // 联系人备注
        List<ContactsRemark> contactsRemarkList = new ArrayList<>();
        for (ClueRemark c : clueRemarkList) {
            ContactsRemark contactsRemark = new ContactsRemark();
            contactsRemark.setId(UUIDUtil.getUUID());
            contactsRemark.setContactsid(contacts.getId());
            contactsRemark.setCreateby(userid);
            contactsRemark.setCreatetime(DateTimeUtil.getSysTime());
            contactsRemark.setEditflag("0");
            contactsRemark.setNotecontent(c.getNotecontent());
            contactsRemarkList.add(contactsRemark);
        }
        contactsRemarkDao.batchInsert(contactsRemarkList);

        // 联系人市场活动关系表
        List<ClueActivityRelation> carList = clue.getCarList();
        List<ContactsActivityRelation> list = new ArrayList<>();
        for (ClueActivityRelation c : carList) {
            ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setActivityid(c.getActivityid());
            contactsActivityRelation.setContactsid(contacts.getId());
            list.add(contactsActivityRelation);
        }
        contactsActivityRelationDao.batchInsert(list);

        // 添加交易与交易历史记录（如果有）
        if (tranflag) {
            tran.setId(UUIDUtil.getUUID());
            tran.setOwner(clue.getOwner());
            tran.setCustomerid(customer.getId());
            tran.setSource(clue.getSource());
            tran.setContactsummary(contacts.getContactsummary());
            tran.setCreateby(userid);
            tran.setCreatetime(DateTimeUtil.getSysTime());
            tran.setContactsid(contacts.getId());
            tranDao.insert(tran);

            TranHistory tranHistory = new TranHistory();
            tranHistory.setId(UUIDUtil.getUUID());
            tranHistory.setCreateby(userid);
            tranHistory.setCreatetime(DateTimeUtil.getSysTime());
            tranHistory.setExpecteddate(tran.getExpecteddate());
            tranHistory.setMoney(tran.getMoney());
            tranHistory.setStage(tran.getStage());
            tranHistory.setTranid(tran.getId());
            tranHistoryDao.insert(tranHistory);
        }

        // 删除clueid对应的线索 备注 关系记录
        clueDao.deleteById(clueid);
        clueRemarkDao.deleteByClueId(clueid);
        clueActivityRelationDao.deleteByClueId(clueid);
    }
}