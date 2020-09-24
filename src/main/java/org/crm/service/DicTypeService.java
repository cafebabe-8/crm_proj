package org.crm.service;

import org.crm.entity.DicType;

import java.util.List;

/**
 * (DicType)表服务接口
 *
 * @author makejava
 * @since 2020-09-03 16:46:26
 */
public interface DicTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    DicType queryById(String code);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DicType> queryAllByLimit(int offset, int limit);



    List<DicType> queryAll(DicType dicType);

    /**
     * 新增数据
     *
     * @param dicType 实例对象
     * @return 实例对象
     */
    DicType insert(DicType dicType);

    /**
     * 修改数据
     *
     * @param dicType 实例对象
     * @return 实例对象
     */
    DicType update(DicType dicType);

    /**
     * 通过主键删除数据
     *
     * @param codeArr
     * @return 是否成功
     */
    boolean deleteById(String[] codeArr);

}