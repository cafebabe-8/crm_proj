package org.crm.service.impl;

import org.crm.dao.ClueActivityRelationDao;
import org.crm.entity.ClueActivityRelation;
import org.crm.service.ClueActivityRelationService;
import org.crm.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (ClueActivityRelation)表服务实现类
 *
 * @author makejava
 * @since 2020-09-11 19:58:43
 */
@Service("clueActivityRelationService")
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {
    @Resource
    private ClueActivityRelationDao clueActivityRelationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ClueActivityRelation queryById(String id) {
        return this.clueActivityRelationDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ClueActivityRelation> queryAllByLimit(int offset, int limit) {
        return this.clueActivityRelationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param clueActivityRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ClueActivityRelation insert(ClueActivityRelation clueActivityRelation) {
        this.clueActivityRelationDao.insert(clueActivityRelation);
        return clueActivityRelation;
    }

    /**
     * 修改数据
     *
     * @param clueActivityRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ClueActivityRelation update(ClueActivityRelation clueActivityRelation) {
        this.clueActivityRelationDao.update(clueActivityRelation);
        return this.queryById(clueActivityRelation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.clueActivityRelationDao.deleteById(id) > 0;
    }

    /**
     * 根据线索的id和活动的id删除指定行
     * @param clueId
     * @param actId
     */
    @Override
    public void deleteOneRelation(String clueId, String actId) {
        clueActivityRelationDao.deleteOneRelation(clueId, actId);
    }


    /**
     * 先删除线索id对应的所有关系 再增加记录
     * @param clueId
     * @param actIds
     */
    @Override
    public void insertRelation(String clueId, String[] actIds) {
        // 删除clueId对应的所有的关系记录
        clueActivityRelationDao.deleteByClueId(clueId);
        // 新增关系记录
        List<ClueActivityRelation> relationList = new ArrayList<>();
        for (String actId : actIds) {
            ClueActivityRelation car = new ClueActivityRelation();
            car.setId(UUIDUtil.getUUID());
            car.setClueid(clueId);
            car.setActivityid(actId);
            relationList.add(car);
        }
        clueActivityRelationDao.batchInsert(relationList);
    }
}