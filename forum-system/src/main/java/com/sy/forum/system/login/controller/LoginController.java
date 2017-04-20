package com.sy.forum.system.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author SY
 * @ClassName LoginController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Date 2017-04-20 10:24
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/initLoginPage")
    public String initLoginPage(Model model) {
        model.addAttribute("hello", "Hello, My name is Carl! What't you name?");
        System.out.println("Carl init main page");
        return "index";
    }
}
