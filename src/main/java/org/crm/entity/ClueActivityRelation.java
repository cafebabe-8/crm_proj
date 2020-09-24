package org.crm.entity;

import java.io.Serializable;

/**
 * (ClueActivityRelation)实体类
 *
 * @author makejava
 * @since 2020-09-11 19:58:43
 */
public class ClueActivityRelation implements Serializable {
    private static final long serialVersionUID = 249418280182628925L;

    private String id;

    private String clueid;

    private String activityid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClueid() {
        return clueid;
    }

    public void setClueid(String clueid) {
        this.clueid = clueid;
    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

}