package org.crm.service.impl;

import org.crm.dao.CustomerRemarkDao;
import org.crm.entity.CustomerRemark;
import org.crm.service.CustomerRemarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CustomerRemark)表服务实现类
 *
 * @author makejava
 * @since 2020-09-14 21:36:54
 */
@Service("customerRemarkService")
public class CustomerRemarkServiceImpl implements CustomerRemarkService {
    @Resource
    private CustomerRemarkDao customerRemarkDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CustomerRemark queryById(String id) {
        return this.customerRemarkDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<CustomerRemark> queryAllByLimit(int offset, int limit) {
        return this.customerRemarkDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param customerRemark 实例对象
     * @return 实例对象
     */
    @Override
    public CustomerRemark insert(CustomerRemark customerRemark) {
        this.customerRemarkDao.insert(customerRemark);
        return customerRemark;
    }

    /**
     * 修改数据
     *
     * @param customerRemark 实例对象
     * @return 实例对象
     */
    @Override
    public CustomerRemark update(CustomerRemark customerRemark) {
        this.customerRemarkDao.update(customerRemark);
        return this.queryById(customerRemark.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.customerRemarkDao.deleteById(id) > 0;
    }
}