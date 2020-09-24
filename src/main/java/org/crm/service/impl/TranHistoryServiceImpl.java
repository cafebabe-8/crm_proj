package org.crm.service.impl;

import org.crm.dao.TranHistoryDao;
import org.crm.entity.TranHistory;
import org.crm.service.TranHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TranHistory)表服务实现类
 *
 * @author makejava
 * @since 2020-09-14 21:36:53
 */
@Service("tranHistoryService")
public class TranHistoryServiceImpl implements TranHistoryService {
    @Resource
    private TranHistoryDao tranHistoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TranHistory queryById(String id) {
        return this.tranHistoryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TranHistory> queryAllByLimit(int offset, int limit) {
        return this.tranHistoryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tranHistory 实例对象
     * @return 实例对象
     */
    @Override
    public TranHistory insert(TranHistory tranHistory) {
        this.tranHistoryDao.insert(tranHistory);
        return tranHistory;
    }

    /**
     * 修改数据
     *
     * @param tranHistory 实例对象
     * @return 实例对象
     */
    @Override
    public TranHistory update(TranHistory tranHistory) {
        this.tranHistoryDao.update(tranHistory);
        return this.queryById(tranHistory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tranHistoryDao.deleteById(id) > 0;
    }
}