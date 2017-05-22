package com.sy.forum.system.main.controller;

import com.sy.forum.core.entity.GenericFinal;
import com.sy.forum.core.entity.Result;
import com.sy.forum.core.entity.SessionAttributeFinal;
import com.sy.forum.core.entity.UnitedLogger;
import com.sy.forum.system.users.model.UserInfo;
import com.sy.forum.system.users.service.UserService;
import com.sy.forum.utils.AddressUtils;
import com.sy.forum.utils.LocaleUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author SY
 * @ClassName LoginController
 * @Description: 登录
 * @Date 2017-04-20 10:24
 */
@Controller
@RequestMapping("/system")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private  LocaleResolver localeResolver;
    @Autowired
    private LocaleUtil localeUtil;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView initLoginPage(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "lang", defaultValue = "zh_CN") String lang) {
        ModelAndView view = new ModelAndView();
        view.setViewName("main/login");
        view.addObject("langType", lang);
        return view;
    }

    /**
     * 登录
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public @ResponseBody Result signIn(Model model, HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody UserInfo userInfo,
                                       @ModelAttribute String lang) {
        Result result = new Result();
        try {
//            if(!Utils.isEmpty(lang)) {
//                LocaleUtil.exchangeLocale(request,lang);
//            }
            //登录 token
            UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getLoginName(), userInfo.getLoginPassword());
            //获取当前的Subject
            Subject currentUser = SecurityUtils.getSubject();
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到shiroRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            //登录验证
            currentUser.login(token);
            //验证是否登录成功
            if(currentUser.isAuthenticated()){
                userInfo = userService.getUserInfoByUsernameAndPassword(userInfo);
                // 成功登录，修改登录错误时间、次数、锁定状态
                userService.updateUserLoginInfoByUserId(userInfo.getUserId());
                UserInfo loginInfo = new UserInfo();
                loginInfo.setUserId(userInfo.getUserId());
                String ip = AddressUtils.getIp(request);
                String address = AddressUtils.getAddresses("ip="+ip, "utf-8");
                loginInfo.setCurentLoginIP(ip);
                loginInfo.setCurentLoginAdress(address);
                //登录用户地理位置信息
                userService.insertLoginUserAddressInfo(loginInfo);
                request.getSession().setAttribute(SessionAttributeFinal.USERINFO, userInfo);
                result.setMessage(GenericFinal.MSG_SUCCESS_LOGIN);
                result.setResultCode(GenericFinal.MSG_SUCCESS_CODE);
            } else {
                token.clear();
                result.setMessage(GenericFinal.MSG_FAILED_LOGIN);
                result.setResultCode(GenericFinal.MSG_FAILED_CODE);
            }
        } catch(UnknownAccountException uae){
            UnitedLogger.error(uae);
            System.out.println("对用户[" + userInfo.getLoginName() + "]进行登录验证..验证未通过,未知账户");
            result.setMessage(uae.getMessage());
            result.setResultCode(GenericFinal.MSG_FAILED_CODE);
        } catch(LockedAccountException lae){
            UnitedLogger.error(lae);
            System.out.println("对用户[" + userInfo.getLoginName() + "]进行登录验证..验证未通过,账户已锁定");
            result.setMessage(lae.getMessage());
            result.setResultCode(GenericFinal.MSG_FAILED_CODE);
        } catch(ExcessiveAttemptsException eae){
            UnitedLogger.error(eae);
            System.out.println("对用户[" + userInfo.getLoginName() + "]进行登录验证..验证未通过,错误次数过多");
            result.setMessage(eae.getMessage());
            result.setResultCode(GenericFinal.MSG_FAILED_CODE);
        } catch(AuthenticationException ae){
            UnitedLogger.error(ae);
            result.setMessage(ae.getMessage());
            result.setResultCode(GenericFinal.MSG_FAILED_CODE);
        }  catch (Exception e) {
            UnitedLogger.error(e);
            result.setMessage(localeUtil.loadLocalString(GenericFinal.MSG_FAILED_UNKNOW));
            result.setResultCode(GenericFinal.MSG_FAILED_CODE);
        }
        return result;
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        return "/system/login";
    }

    /**
     * 错误的请求
     * @return
     */
    @RequestMapping("/400")
    public String errorRequest(){
        return "/400";
    }

    /**
     * 没有权限
     * @return
     */
    @RequestMapping("/401")
    public String unauthorizedRole(){
        return "/401";
    }

    /**
     * 找不到路径
     * @return
     */
    @RequestMapping("/404")
    public String notFountPath(){
        return "/404";
    }

    /**
     * 不允许的方法
     * @return
     */
    @RequestMapping("/405")
    public String methodNotAllowed(){
        return "/405";
    }

    /**
     * 服务器内部错误
     * @return
     */
    @RequestMapping("/500")
    public String serviceInternalError(){
        return "/500";
    }


}
