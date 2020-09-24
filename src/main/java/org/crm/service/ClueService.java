package org.crm.service;

import org.crm.entity.Clue;
import org.crm.entity.Tran;

import java.util.List;

/**
 * (Clue)表服务接口
 *
 * @author makejava
 * @since 2020-09-11 19:58:43
 */
public interface ClueService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Clue queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Clue> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param clue 实例对象
     * @return 实例对象
     */
    Clue insert(Clue clue);

    /**
     * 修改数据
     *
     * @param clue 实例对象
     * @return 实例对象
     */
    Clue update(Clue clue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    void insertOrUpdate(Clue clue, String id);

    List<Clue> queryAllWithUser();


    /**
     * 转换线索
     * @param userid owner的id
     * @param tran  交易对象
     * @param clueid 线索id
     * @param tranflag 是否新增交易记录
     */
    void convert(String userid, Tran tran, String clueid, Boolean tranflag);
}