package com.jiangweijia.community.dto;

import lombok.Data;

/**
 * @author Fisherman
 * @date 2020/5/18 15:06
 */
@Data
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
