package com.jiangweijia.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Fisherman
 * @date 2020/5/17 15:49
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name") String name , Model model){
        model.addAttribute("name",name);
        //如果返回字符串，默认情况下会被解析为模板的名字
        return "hello";
    }
}
