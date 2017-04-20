package com.sy.forum.system.login.controller;

import com.sy.forum.system.users.model.UserInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author SY
 * @ClassName LoginController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Date 2017-04-20 10:24
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/{name}")
    public UserInfo login(@PathVariable("name") String name) {
        return new UserInfo(name,"666666");
    }
}
