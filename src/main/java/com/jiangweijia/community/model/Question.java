package com.jiangweijia.community.model;

import lombok.Data;

/**
 * @author Fisherman
 * @date 2020/5/20 22:15
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
