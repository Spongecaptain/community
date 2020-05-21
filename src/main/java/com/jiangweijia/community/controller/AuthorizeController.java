package com.jiangweijia.community.controller;

import com.jiangweijia.community.dto.AccessTokenDto;
import com.jiangweijia.community.dto.GitHubUser;
import com.jiangweijia.community.mapper.UserMapper;
import com.jiangweijia.community.model.User;
import com.jiangweijia.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    @Autowired private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam("state") String state,
                           @Autowired GithubProvider githubProvider,
                           HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        final AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);

        final String accessToken = githubProvider.getAccessToken(accessTokenDto);

        final GitHubUser gitHubUser = githubProvider.getUser(accessToken);

        if (null != gitHubUser && gitHubUser.getId()!=null){
            final User user = new User();
            final String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.insert(user);

            httpServletResponse.addCookie(new Cookie("token",token));
            System.out.println(token);
            System.out.println(user);
            //登录成功，写 Cookie 以及 Session
            httpServletRequest.getSession().setAttribute("user",user);
            return "redirect:/";
        }else{
            //登录失败，尝试重新登录
            return "redirect:/";
        }





    }

}
