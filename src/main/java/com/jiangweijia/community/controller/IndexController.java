package com.jiangweijia.community.controller;

import com.jiangweijia.community.mapper.UserMapper;
import com.jiangweijia.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Fisherman
 * @date 2020/5/17 15:49
 */
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        String token = null;
        final Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
                break;
            }
        }
        if(null !=token){
            User user = userMapper.findByToken(token);
            if(null != user){
                request.getSession().setAttribute("user",user);
                System.out.println(user.getName());
            }
        }
        //如果返回字符串，默认情况下会被解析为模板的名字
        return "index";
    }

}
