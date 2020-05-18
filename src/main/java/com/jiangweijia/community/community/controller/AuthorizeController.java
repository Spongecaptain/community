package com.jiangweijia.community.community.controller;

import com.jiangweijia.community.community.dto.AccessTokenDto;
import com.jiangweijia.community.community.dto.GitHubUser;
import com.jiangweijia.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Fisherman
 * @date 2020/5/18 13:59
 */

@Controller
public class AuthorizeController {

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam("state") String state, @Autowired GithubProvider githubProvider) {
        final AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);

        final String accessToken = githubProvider.getAccessToken(accessTokenDto);

        final GitHubUser user = githubProvider.getUser(accessToken);

        System.out.println(user);

        return "index";

    }

}
