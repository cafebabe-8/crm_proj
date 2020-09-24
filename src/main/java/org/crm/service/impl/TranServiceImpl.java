package org.crm.service.impl;

import org.crm.dao.TranDao;
import org.crm.dao.TranHistoryDao;
import org.crm.entity.Tran;
import org.crm.entity.TranHistory;
import org.crm.service.TranService;
import org.crm.utils.DateTimeUtil;
import org.crm.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Tran)表服务实现类
 *
 * @author makejava
 * @since 2020-09-14 21:36:53
 */
@Service("tranService")
public class TranServiceImpl implements TranService {
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
    public Tran queryById(String id) {
        return this.tranDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Tran> queryAllByLimit(int offset, int limit) {
        return this.tranDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tran 实例对象
     * @return 实例对象
     */
    @Override
    public Tran insert(Tran tran) {
        this.tranDao.insert(tran);
        return tran;
    }

    /**
     * 修改数据
     *
     * @param tran 实例对象
     * @return 实例对象
     */
    @Override
    public Tran update(Tran tran) {
        this.tranDao.update(tran);
        return this.queryById(tran.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tranDao.deleteById(id) > 0;
    }

    /**
     * 更新指定id的线索的状态字段
     * @param id 当前登录用户的id
     * @param tran 更新用的数据
     */
    @Override
    public void changeState(String id, Tran tran) {
        // 设置编辑记录
        tran.setEditby(id);
        tran.setEdittime(DateTimeUtil.getSysTime());
        tranDao.update(tran);

        // 在交易历史中也要新增记录
        TranHistory tranHistory = new TranHistory();
        tranHistory.setId(UUIDUtil.getUUID());
        tranHistory.setTranid(tran.getId());
        tranHistory.setStage(tran.getStage());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setExpecteddate(tran.getExpecteddate());
        tranHistory.setCreatetime(DateTimeUtil.getSysTime());
        tranHistory.setCreateby(id);
        tranHistoryDao.insert(tranHistory);
    }
}