package com.jiangweijia.community.provider;

import com.alibaba.fastjson.JSON;
import com.jiangweijia.community.dto.AccessTokenDto;
import com.jiangweijia.community.dto.GitHubUser;
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
            //因为经常出现 GitHub 无法直连的情况，所以这里通常会出现异常
            System.out.println("https://github.com/login/oauth/access_token 连接失败");
            return "连接失败";
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

    //因为服务端没有代理，所以很经常出现连接失败的情况，这里就添加一个默认的用户，来提供连接
    public GitHubUser getUserIfConnectFail(){
        final GitHubUser gitHubUser = new GitHubUser();
        gitHubUser.setAvatarUrl("https://avatars0.githubusercontent.com/u/48505670?v=4");
        gitHubUser.setId(48505670L);
        gitHubUser.setName("wjjiang");
        return gitHubUser;
    }
}
