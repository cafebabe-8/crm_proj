package org.crm.entity;

import java.io.Serializable;

/**
 * (ContactsActivityRelation)实体类
 *
 * @author makejava
 * @since 2020-09-14 21:36:55
 */
public class ContactsActivityRelation implements Serializable {
    private static final long serialVersionUID = 565832465646287344L;

    private String id;

    private String contactsid;

    private String activityid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactsid() {
        return contactsid;
    }

    public void setContactsid(String contactsid) {
        this.contactsid = contactsid;
    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

}