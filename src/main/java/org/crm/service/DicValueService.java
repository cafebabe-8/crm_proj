package org.crm.service;

import org.crm.entity.DicValue;

import java.util.List;

/**
 * (DicValue)表服务接口
 *
 * @author makejava
 * @since 2020-09-04 19:10:08
 */
public interface DicValueService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DicValue queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DicValue> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param dicValue 实例对象
     * @return 实例对象
     */
    DicValue insert(DicValue dicValue);

    /**
     * 修改数据
     *
     * @param dicValue 实例对象
     * @return 实例对象
     */
    DicValue update(DicValue dicValue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<DicValue> queryAll(DicValue dicValue);

   boolean deleteByIds(String[] ids);

}