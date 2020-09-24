package org.crm.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Tran)实体类
 *
 * @author makejava
 * @since 2020-09-14 21:36:53
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Tran implements Serializable {
    private static final long serialVersionUID = 617114671624278147L;

    private String id;

    private String owner;

    private String money;

    private String name;

    private String expecteddate;

    private String customerid;

    private String stage;

    private String type;

    private String source;

    private String activityid;

    private String contactsid;

    private String createby;

    private String createtime;

    private String editby;

    private String edittime;

    private String description;

    private String contactsummary;

    private String nextcontacttime;

    // owner对应的User
    private TblUser user;
    // 创建者
    private TblUser creator;
    // 编辑者
    private TblUser editor;
    // customerid对应的客户
    private Customer customer;
    // activityid对应的活动
    private Activity activity;
    // contactsid对应的联系人
    private Contacts contacts;




}