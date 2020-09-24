package org.crm.service.impl;

import org.crm.dao.ActivityRemarkDao;
import org.crm.entity.ActivityRemark;
import org.crm.service.ActivityRemarkService;
import org.crm.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * (ActivityRemark)表服务实现类
 *
 * @author makejava
 * @since 2020-09-10 15:20:41
 */
@Service("activityRemarkService")
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Resource
    private ActivityRemarkDao activityRemarkDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ActivityRemark queryById(String id) {
        return this.activityRemarkDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ActivityRemark> queryAllByLimit(int offset, int limit) {
        return this.activityRemarkDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param activityRemark 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(ActivityRemark activityRemark) {
        activityRemark.setId(UUID.randomUUID().toString().replace("-", ""));
        activityRemark.setCreatetime(DateTimeUtil.getSysTime());
        activityRemark.setEditflag("0");
        activityRemarkDao.insert(activityRemark);
    }

    /**
     * 修改数据
     *
     * @param activityRemark 实例对象
     * @return 实例对象
     */
    @Override
    public void update(ActivityRemark activityRemark) {
        // 组装对象的属性
        activityRemark.setEditflag("1");
        activityRemark.setEdittime(DateTimeUtil.getSysTime());
        activityRemarkDao.update(activityRemark);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.activityRemarkDao.deleteById(id) > 0;
    }


    /**
     * 通过活动的id来查询对应的所有的备注
     * @param activityid 活动id
     * @return 对应的所有记录组成的列表
     */
    @Override
    public List<ActivityRemark> queryByActivityId(String activityid) {
        return activityRemarkDao.queryByActivityId(activityid);
    }
}