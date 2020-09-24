package org.crm.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * (Clue)实体类
 *
 * @author makejava
 * @since 2020-09-11 19:58:42
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Clue implements Serializable {
    private static final long serialVersionUID = -70951701457769177L;

    private String id;

    private String fullname;

    private String appellation;

    private String owner;

    private String company;

    private String job;

    private String email;

    private String phone;

    private String website;

    private String mphone;

    private String state;

    private String source;

    private String createby;

    private String createtime;

    private String editby;

    private String edittime;

    private String description;

    private String contactsummary;

    private String nextcontacttime;

    private String address;

    // 所有者id对应的user对象
    private TblUser user;

    private  TblUser creator;

    private  TblUser editor;

    // 备注列表
    private List<ClueRemark> clueRemarkList;

    // 线索与市场活动关系列表
    private List<ClueActivityRelation> carList;

}