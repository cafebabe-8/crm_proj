package org.crm.service.impl;

import org.crm.dao.ActivityDao;
import org.crm.dao.ClueActivityRelationDao;
import org.crm.dao.TblUserDao;
import org.crm.entity.Activity;
import org.crm.entity.Page;
import org.crm.entity.TblUser;
import org.crm.service.ActivityService;
import org.crm.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * (Activity)表服务实现类
 *
 * @author makejava
 * @since 2020-09-05 16:21:39
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityDao activityDao;

    @Resource
    private TblUserDao userDao;

    @Override
    public List<Activity> queryAll() {
        return activityDao.queryAll(new Activity());
    }

    @Override
    public void queryAllForPage(Page page, Activity activity) {
        // 组装page中的数据
        // private Integer currentPage = 1; // 当前页
        //    private Integer rowsPerPage = 10; // 每页条数
        //    private Integer maxRowsPerPage = 100; // 每页最多显示多少条
        //    private Integer totalRows; // 总记录数
        //    private Integer totalPages; // 总页数
        //    private Integer visiblePageLinks = 5; // 页面上显示的分页按钮数
        //    private List<?> list; // 当前页数据

        // 获得总记录数
        Integer totalRows = activityDao.total(activity);
        // 计算总的页数
        Integer totalPages = (totalRows - 1) / page.getRowsPerPage() + 1;
        // 当前的页面数据
        // 获取offset
        Integer offset = page.getRowsPerPage() * (page.getCurrentPage() - 1);
        // 查询数据
        List<Activity> list = activityDao.queryAllByLimitForPage(offset, page.getRowsPerPage(), activity);

        page.setTotalRows(totalRows);
        page.setTotalPages(totalPages);
        page.setList(list);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return activityDao.deleteByIds(ids);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Activity queryById(String id) {
        return this.activityDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Activity> queryAllByLimit(int offset, int limit) {
        return this.activityDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Activity activity, String id) {
        // 组装activity对象
        if ("".equals(activity.getId())) {
            // 为创建操作
            activity.setId(UUID.randomUUID().toString().replace("-", ""));
            activity.setCreateby(id);
            activity.setCreatetime(DateTimeUtil.getSysTime());
            return activityDao.insert(activity);
        } else {
            activity.setEditby(id);
            activity.setEdittime(DateTimeUtil.getSysTime());
            return activityDao.update(activity);
        }

    }

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Activity activity) {
        return this.activityDao.update(activity);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.activityDao.deleteById(id) > 0;
    }

    @Override
    public void deleteForPagination(Activity activity, String[] in, String[] notin, boolean lastState, boolean selectall) {
        activityDao.deleteForPagination(activity, in, notin, lastState, selectall);
    }


    @Override
    public List<Activity> queryAllForExport(Activity activity, String[] in, String[] notin, boolean lastState, boolean selectall) {
        return activityDao.queryAllForExport(activity, in, notin, lastState, selectall);
    }

    @Override
    public void insertImported(List<Activity> activityList) {
        // 将用户的名字对应的id查出来放入activity对象中
        for (Activity act : activityList){
            String name = act.getUser().getName();
            TblUser tmpUser = new TblUser();
            tmpUser.setName(name);
            TblUser user = userDao.querybyUser(tmpUser);
            act.setOwner(user.getId());
        }
        activityDao.insertImported(activityList);
    }

    @Override
    public List<Activity> queryReferAct(String clueId) {
        return activityDao.queryReferAct(clueId);
    }

    @Override
    public List<Activity> fuzzyQuery(String keyword) {
        return activityDao.fuzzyQuery(keyword);
    }
}

