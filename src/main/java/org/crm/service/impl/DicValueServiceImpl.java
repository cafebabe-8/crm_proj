package org.crm.service.impl;

import org.crm.dao.DicValueDao;
import org.crm.entity.DicValue;
import org.crm.service.DicValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * (DicValue)表服务实现类
 *
 * @author makejava
 * @since 2020-09-04 19:10:08
 */
@Service("dicValueService")
public class DicValueServiceImpl implements DicValueService {
    @Resource
    private DicValueDao dicValueDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DicValue queryById(String id) {
        return this.dicValueDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<DicValue> queryAllByLimit(int offset, int limit) {
        return this.dicValueDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dicValue 实例对象
     * @return 实例对象
     */
    @Override
    public DicValue insert(DicValue dicValue) {
        this.dicValueDao.insert(dicValue);
        return dicValue;
    }

    /**
     * 修改数据
     *
     * @param dicValue 实例对象
     * @return 实例对象
     */
    @Override
    public DicValue update(DicValue dicValue) {
        // 如果id为空 表示是增加操作
        if ("".equals(dicValue.getId())){
            // 为id设定uuid
            dicValue.setId(UUID.randomUUID().toString().replace("-", ""));
            this.dicValueDao.insert(dicValue);
        } else {
            this.dicValueDao.update(dicValue);
        }
        return this.queryById(dicValue.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.dicValueDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByIds(String[] ids) {
        return this.dicValueDao.deleteByIds(ids) > 0;
    }

    @Override
    public List<DicValue> queryAll(DicValue dicValue) {
        return this.dicValueDao.queryAll(dicValue);
    }
}