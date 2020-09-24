package org.crm.service;

import org.crm.entity.TblUser;
import org.crm.exception.LoginException;

import java.util.List;

/**
 * (TblUser)表服务接口
 *
 * @author makejava
 * @since 2020-09-01 15:41:53
 */
public interface TblUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TblUser queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TblUser> queryAllByLimit(int offset, int limit);


    TblUser querybyUser(TblUser user);

    /**
     * 新增数据
     *
     * @param tblUser 实例对象
     * @return 实例对象
     */
    TblUser insert(TblUser tblUser);

    /**
     * 修改数据
     *
     * @param tblUser 实例对象
     * @return 实例对象
     */
    TblUser update(TblUser tblUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);


    // 校验登录信息
    TblUser validateLogin(String username, String password, String ip) throws LoginException;

    // 验证cookie中的信息
    TblUser validateLoginforAutoLogin(String username, String password, String ip);

    List<TblUser> queryAll();

}