package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Activity)实体类
 *
 * @author makejava
 * @since 2020-09-05 16:21:39
 */
@Data
@NoArgsConstructor
public class Activity implements Serializable {
    private static final long serialVersionUID = -50038518408008620L;

    private String id;

    private String owner;

    private String name;

    private String startdate;

    private String enddate;

    private String cost;

    private String description;

    private String createtime;

    private String createby;

    private String edittime;

    private String editby;



}