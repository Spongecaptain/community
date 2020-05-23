package com.jiangweijia.community.controller;

import com.jiangweijia.community.dto.PaginationDTO;
import com.jiangweijia.community.dto.QuestionDTO;
import com.jiangweijia.community.mapper.UserMapper;
import com.jiangweijia.community.model.User;
import com.jiangweijia.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Fisherman
 * @date 2020/5/17 15:49
 */
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        String token = null;
        final Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        if (null != token) {
            User user = userMapper.findByToken(token);
            if (null != user) {
                request.getSession().setAttribute("user", user);
                System.out.println(user.getName());
            }
        }

        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination",pagination);

        //如果返回字符串，默认情况下会被解析为模板的名字
        return "index";
    }

}