package com.sy.forum.system.main.controller;

import org.springframework.context.annotation.ComponentScan;
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
@RequestMapping("/rest/home")
public class HomeController {

    @RequestMapping("/homePage")
    public String home(Model model) {
        model.addAttribute("pageBodyTemplate","pageBody");
        model.addAttribute("pageBodyFragment","pageBody");
//        model.addAttribute("pageBodyTemplate","roles/role_list");
//        model.addAttribute("pageBodyFragment","roleInitBody");
        return "main/home";
    }
}
