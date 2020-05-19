package com.jiangweijia.community.dto;

import lombok.Data;

/**
 * @author Fisherman
 * @date 2020/5/18 14:12
 */

@Data
public class AccessTokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
