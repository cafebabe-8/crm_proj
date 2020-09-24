package org.crm.entity;

import java.io.Serializable;

/**
 * (TranHistory)实体类
 *
 * @author makejava
 * @since 2020-09-14 21:36:53
 */
public class TranHistory implements Serializable {
    private static final long serialVersionUID = -33076827221655311L;

    private String id;

    private String stage;

    private String money;

    private String expecteddate;

    private String createtime;

    private String createby;

    private String tranid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getExpecteddate() {
        return expecteddate;
    }

    public void setExpecteddate(String expecteddate) {
        this.expecteddate = expecteddate;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getTranid() {
        return tranid;
    }

    public void setTranid(String tranid) {
        this.tranid = tranid;
    }

}