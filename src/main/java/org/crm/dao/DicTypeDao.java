package org.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.crm.entity.DicType;

import java.util.List;

/**
 * (DicType)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-03 16:46:26
 */
public interface DicTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    DicType queryById(String code);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DicType> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dicType 实例对象
     * @return 对象列表
     */
    List<DicType> queryAll(DicType dicType);

    /**
     * 新增数据
     *
     * @param dicType 实例对象
     * @return 影响行数
     */
    int insert(DicType dicType);

    /**
     * 修改数据
     *
     * @param dicType 实例对象
     * @return 影响行数
     */
    int update(DicType dicType);

    /**
     * 通过主键删除数据
     *
     *
     * @return 影响行数
     */
    int deleteById(String[] codeArr);

}