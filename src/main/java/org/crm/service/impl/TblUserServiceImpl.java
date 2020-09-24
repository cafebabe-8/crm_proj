package org.crm.service.impl;

import org.crm.dao.TblUserDao;
import org.crm.entity.TblUser;
import org.crm.exception.LoginException;
import org.crm.service.TblUserService;
import org.crm.utils.DateTimeUtil;
import org.crm.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (TblUser)表服务实现类
 *
 * @author makejava
 * @since 2020-09-01 15:41:53
 */
@Service("tblUserService")
public class TblUserServiceImpl implements TblUserService {
    @Resource
    private TblUserDao tblUserDao;





    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TblUser queryById(String id) {
        return this.tblUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TblUser> queryAllByLimit(int offset, int limit) {
        return this.tblUserDao.queryAllByLimit(offset, limit);
    }

    @Override
    public TblUser querybyUser(TblUser user) {
        return this.tblUserDao.querybyUser(user);
    }

    /**
     * 新增数据
     *
     * @param tblUser 实例对象
     * @return 实例对象
     */
    @Override
    public TblUser insert(TblUser tblUser) {
        this.tblUserDao.insert(tblUser);
        return tblUser;
    }

    /**
     * 修改数据
     *
     * @param tblUser 实例对象
     * @return 实例对象
     */
    @Override
    public TblUser update(TblUser tblUser) {
        this.tblUserDao.update(tblUser);
        return this.queryById(tblUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tblUserDao.deleteById(id) > 0;
    }

    @Override
    public TblUser validateLogin(String username, String password, String ip) throws LoginException {
        // 对password进行MD5加密
        String crpytedPwd = MD5Util.getMD5(password);
        // 在数据库中查询数据
        TblUser user = new TblUser();
        user.setLoginact(username);
        user.setLoginpwd(crpytedPwd);
        TblUser userinTbl = tblUserDao.querybyUser(user);

        // 判断用户名或者密码是否正确
        if (userinTbl == null){
            throw new LoginException("用户名或者密码错误!");
        }

        // 判断用户记录是否过期
        // 先将记录中的日期转换成时间戳
        long expireTimeMillis = DateTimeUtil.str2Date(userinTbl.getExpiretime()).getTime();
        // 获取现在的时间
        long currentTimeMillis = System.currentTimeMillis();
        // 过期则抛出异常
        if (expireTimeMillis < currentTimeMillis){
            throw new LoginException("用户已过期!");
        }

        // 判断账号是否被锁定 0表示被锁定 1表示正常
        if ("0".equals(userinTbl.getLockstate())){
            throw new LoginException("用户已被锁定!");
        }

        // 判断用户是否允许访问当前请求的ip地址
        String allowedIPs = userinTbl.getAllowips();
        if (!"".equals(allowedIPs) && !allowedIPs.contains(ip)){
            throw new LoginException("当前IP不允许访问！");
        }

        return userinTbl;


    }


    @Override
    public TblUser validateLoginforAutoLogin(String username, String password, String ip) {
        // cookie中的密码已经是密文 不需要再次加密
        // 在数据库中查询数据
        TblUser user = new TblUser();
        user.setLoginact(username);
        user.setLoginpwd(password);
        TblUser userinTbl = tblUserDao.querybyUser(user);

        // 判断用户名或者密码是否正确
        if (userinTbl == null){
            return null;
        }

        // 判断用户记录是否过期
        // 先将记录中的日期转换成时间戳
        long expireTimeMillis = DateTimeUtil.str2Date(userinTbl.getExpiretime()).getTime();
        // 获取现在的时间
        long currentTimeMillis = System.currentTimeMillis();
        // 过期则抛出异常
        if (expireTimeMillis < currentTimeMillis){
            return null;
        }

        // 判断账号是否被锁定 0表示被锁定 1表示正常
        if ("0".equals(userinTbl.getLockstate())){
            return null;
        }

        // 判断用户是否允许访问当前请求的ip地址
        String allowedIPs = userinTbl.getAllowips();
        if (!"".equals(allowedIPs) && !allowedIPs.contains(ip)){
            return null;
        }

        return userinTbl;
    }

    @Override
    public List<TblUser> queryAll() {
        return tblUserDao.queryAll(new TblUser());
    }
}