package com.sy.forum.system.login.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.sy.forum.core.entity.GenericFinalMSG;
import com.sy.forum.core.entity.Result;
import com.sy.forum.core.entity.UnitedLogger;
import com.sy.forum.exceptions.UnitedException;
import com.sy.forum.system.users.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author SY
 * @ClassName LoginController
 * @Description: 登录
 * @Date 2017-04-20 10:24
 */
@Controller
@RequestMapping("/rest/login")
public class LoginController {

    @RequestMapping("/initLoginPage")
    public ModelAndView initLoginPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("main/login");
        return view;
    }

    /**
     * 登录
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public @ResponseBody Result signIn(@RequestBody UserInfo userInfo) {
        Result result = new Result();
        try {
            System.out.println(userInfo.getUserName() + "正在登录Forum系统...");
            result.setMessage(GenericFinalMSG.SUCCESS_LOGIN_MSG);
            result.setResultCode(GenericFinalMSG.SUCCESS_CODE);
        } catch (Exception e) {
            UnitedLogger.error(e);
            result.setMessage(GenericFinalMSG.FAILED_UNKNOW_MSG);
            result.setResultCode(GenericFinalMSG.FAILED_CODE);
        }
        return result;
    }
}
