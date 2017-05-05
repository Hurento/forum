package com.sy.forum.system.main.controller;

import com.sy.forum.system.main.model.MenuItems;
import com.sy.forum.system.main.service.MenuItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author SY
 * @ClassName LoginController
 * @Description: 登录
 * @Date 2017-04-20 10:24
 */
@Controller
@RequestMapping("/rest/home")
public class HomeController {

    @Autowired
    private MenuItemsService menuItemsService;

    @RequestMapping("/homePage")
    public String home(Model model) {
        // 查询菜单
        List<MenuItems> list = menuItemsService.findMenuItemsList(null);
        model.addAttribute("menuItems", list);
        model.addAttribute("pageBodyTemplate","pageBody");
        model.addAttribute("pageBodyFragment","pageBody");
//        model.addAttribute("pageBodyTemplate","roles/role_list");
//        model.addAttribute("pageBodyFragment","roleInitBody");
        return "main/home";
    }
}
