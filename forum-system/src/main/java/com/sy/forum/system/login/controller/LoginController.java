package com.sy.forum.system.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author SY
 * @ClassName LoginController
 * @Description: 登录
 * @Date 2017-04-20 10:24
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/initLoginPage")
    public String initLoginPage(Model model) {
        model.addAttribute("hello", "Hello, My name is Carl! What't you name?");
        System.out.println("Carl init main page");
        return "main/login";
    }
}
