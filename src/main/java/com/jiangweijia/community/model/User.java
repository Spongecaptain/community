package com.jiangweijia.community.model;

import lombok.Data;

/**
 * @author Fisherman
 * @date 2020/5/18 20:44
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
}
