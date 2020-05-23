package com.jiangweijia.community.dto;

import com.jiangweijia.community.model.User;
import lombok.Data;

/**
 * @author Fisherman
 * @date 2020/5/21 19:45
 */
@Data
public class QuestionDTO {
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
    private User user;
}