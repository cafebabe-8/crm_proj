package org.crm.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * (ActivityRemark)实体类
 *
 * @author makejava
 * @since 2020-09-10 15:20:40
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ActivityRemark implements Serializable {
    private static final long serialVersionUID = 302867232988367375L;

    private String id;

    private String notecontent;

    private String createtime;

    private String createby;

    private String edittime;

    private String editby;
    /**
     * 0表示未修改，1表示已修改
     */
    private String editflag;

    private String activityid;

    private TblUser creator;
    private TblUser editor;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotecontent() {
        return notecontent;
    }

    public void setNotecontent(String notecontent) {
        this.notecontent = notecontent;
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

    public String getEdittime() {
        return edittime;
    }

    public void setEdittime(String edittime) {
        this.edittime = edittime;
    }

    public String getEditby() {
        return editby;
    }

    public void setEditby(String editby) {
        this.editby = editby;
    }

    public String getEditflag() {
        return editflag;
    }

    public void setEditflag(String editflag) {
        this.editflag = editflag;
    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    public TblUser getCreator() {
        return creator;
    }

    public void setCreator(TblUser creator) {
        this.creator = creator;
    }

    public TblUser getEditor() {
        return editor;
    }

    public void setEditor(TblUser editor) {
        this.editor = editor;
    }
}