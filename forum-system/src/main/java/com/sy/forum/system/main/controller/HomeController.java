package com.sy.forum.system.main.controller;

import com.sy.forum.core.entity.GenericFinal;
import com.sy.forum.core.entity.SessionAttributeFinal;
import com.sy.forum.system.main.model.MenuItems;
import com.sy.forum.system.main.service.MenuItemsService;
import com.sy.forum.system.users.model.UserInfo;
import com.sy.forum.utils.LocaleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author SY
 * @ClassName LoginController
 * @Description: HOME
 * @Date 2017-04-20 10:24
 */
@Controller
@RequestMapping("/rest/home")
public class HomeController {

    @Autowired
    private MenuItemsService menuItemsService;
    @Autowired
    private LocaleUtil localeUtil;

    /**
     * 页面跳转
     * @param model
     * @param tempKey 页面模板路径
     * @param contentKey 页面模板内容
     * @return
     */
    @RequestMapping("/homePage/{tempKey}/{contentKey}")
    public ModelAndView home(Model model, @PathVariable(value = "tempKey") String tempKey,
                             @PathVariable(value = "contentKey") String contentKey,
                             HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        try {
            //获取当前登录用户
            UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionAttributeFinal.USERINFO);
            String lang = (String)request.getSession().getAttribute(SessionAttributeFinal.LOCALE);
            LocaleUtil.exchangeLocale(request, lang);
            tempKey = GenericFinal.KEY + GenericFinal.POINT + tempKey;// 模板路径
            contentKey = GenericFinal.CONTENT + GenericFinal.POINT + contentKey;// 模板Fragment键

            // 查询菜单
            StringBuffer htmlTemp = menuItemsService.findMenuItemsList(new MenuItems(GenericFinal.NUMBER_ZERO));
            model.addAttribute("htmlTemp", htmlTemp.toString());
            model.addAttribute("pageBodyTemplate", localeUtil.loadLocalString(tempKey));
            model.addAttribute("pageBodyFragment", localeUtil.loadLocalString(contentKey));
            model.addAttribute("currentLoginName", userInfo.getUserName());
            view.setViewName("main/home");
        } catch(NoSuchMessageException e) {
            view.setViewName("redirect:/system/404");
        }
        return view;
    }

}
