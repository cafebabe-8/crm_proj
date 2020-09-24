package org.crm.service.impl;

import org.crm.dao.TranRemarkDao;
import org.crm.entity.TranRemark;
import org.crm.service.TranRemarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TranRemark)表服务实现类
 *
 * @author makejava
 * @since 2020-09-14 21:36:52
 */
@Service("tranRemarkService")
public class TranRemarkServiceImpl implements TranRemarkService {
    @Resource
    private TranRemarkDao tranRemarkDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TranRemark queryById(String id) {
        return this.tranRemarkDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TranRemark> queryAllByLimit(int offset, int limit) {
        return this.tranRemarkDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tranRemark 实例对象
     * @return 实例对象
     */
    @Override
    public TranRemark insert(TranRemark tranRemark) {
        this.tranRemarkDao.insert(tranRemark);
        return tranRemark;
    }

    /**
     * 修改数据
     *
     * @param tranRemark 实例对象
     * @return 实例对象
     */
    @Override
    public TranRemark update(TranRemark tranRemark) {
        this.tranRemarkDao.update(tranRemark);
        return this.queryById(tranRemark.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tranRemarkDao.deleteById(id) > 0;
    }
}