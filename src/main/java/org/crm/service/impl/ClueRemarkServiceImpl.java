package org.crm.service.impl;

import org.crm.dao.ClueRemarkDao;
import org.crm.entity.ClueRemark;
import org.crm.service.ClueRemarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ClueRemark)表服务实现类
 *
 * @author makejava
 * @since 2020-09-11 19:58:44
 */
@Service("clueRemarkService")
public class ClueRemarkServiceImpl implements ClueRemarkService {
    @Resource
    private ClueRemarkDao clueRemarkDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ClueRemark queryById(String id) {
        return this.clueRemarkDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ClueRemark> queryAllByLimit(int offset, int limit) {
        return this.clueRemarkDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param clueRemark 实例对象
     * @return 实例对象
     */
    @Override
    public ClueRemark insert(ClueRemark clueRemark) {
        this.clueRemarkDao.insert(clueRemark);
        return clueRemark;
    }

    /**
     * 修改数据
     *
     * @param clueRemark 实例对象
     * @return 实例对象
     */
    @Override
    public ClueRemark update(ClueRemark clueRemark) {
        this.clueRemarkDao.update(clueRemark);
        return this.queryById(clueRemark.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.clueRemarkDao.deleteById(id) > 0;
    }
}