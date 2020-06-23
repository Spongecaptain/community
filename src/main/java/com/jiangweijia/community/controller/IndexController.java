package com.jiangweijia.community.controller;

import com.jiangweijia.community.dto.PaginationDTO;
import com.jiangweijia.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Fisherman
 * @date 2020/5/17 15:49
 */
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index( Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination",pagination);

        //如果返回字符串，默认情况下会被解析为模板的名字
        return "index";
    }

}