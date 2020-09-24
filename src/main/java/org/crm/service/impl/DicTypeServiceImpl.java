package org.crm.service.impl;

import org.crm.dao.DicTypeDao;
import org.crm.entity.DicType;
import org.crm.service.DicTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DicType)表服务实现类
 *
 * @author makejava
 * @since 2020-09-03 16:46:26
 */
@Service("dicTypeService")
public class DicTypeServiceImpl implements DicTypeService {
    @Resource
    private DicTypeDao dicTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    @Override
    public DicType queryById(String code) {
        return this.dicTypeDao.queryById(code);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<DicType> queryAllByLimit(int offset, int limit) {
        return this.dicTypeDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<DicType> queryAll(DicType dicType) {
        return dicTypeDao.queryAll(dicType);
    }

    /**
     * 新增数据
     *
     * @param dicType 实例对象
     * @return 实例对象
     */
    @Override
    public DicType insert(DicType dicType) {
        this.dicTypeDao.insert(dicType);
        return dicType;
    }

    /**
     * 修改数据
     *
     * @param dicType 实例对象
     * @return 实例对象
     */
    @Override
    public DicType update(DicType dicType) {
        this.dicTypeDao.update(dicType);
        return this.queryById(dicType.getCode());
    }

    /**
     * 通过主键删除数据
     *
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String[] codeArr) {
        return this.dicTypeDao.deleteById(codeArr) > 0;
    }
}