package com.jiangweijia.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.jiangweijia.community.community.dto.AccessTokenDto;
import com.jiangweijia.community.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;



/**
 * @author Fisherman
 * @date 2020/5/18 14:07
 */

@Component

public class GithubProvider {

    public String getAccessToken(AccessTokenDto accessTokenDto) {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            //注意这里的方法 body().string() 而不是 toString()
            String token = response.body().string().split("&")[0].split("=")[1];
            System.out.println(token);
            return token;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try{
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        }catch (Exception e){
            return  null;
        }
    }
}