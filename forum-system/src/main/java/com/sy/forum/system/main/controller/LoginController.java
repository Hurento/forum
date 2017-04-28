package com.sy.forum.system.main.controller;

import com.sy.forum.core.entity.Result;
import com.sy.forum.system.users.service.UserService;
import com.sy.forum.core.entity.GenericFinal;
import com.sy.forum.core.entity.GenericFinalMSG;
import com.sy.forum.core.entity.UnitedLogger;
import com.sy.forum.system.users.model.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

//    @Bean
//    public UserService getUserService(){
//        return new UserServiceImpl();
//    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/initLoginPage", method = RequestMethod.GET)
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
    public Result signIn(@RequestBody UserInfo userInfo, BindingResult bindingResult) {
        Result result = new Result();
        ModelAndView view = new ModelAndView();
        try {
            if(bindingResult.hasErrors()){
               // view.setViewName("redirect:/rest/login/initLoginPage");
                return result;
            }
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
               // view.setViewName("redirect:/rest/home/homePage");
                view.addObject(GenericFinal.RESULTCODE, GenericFinalMSG.SUCCESS_CODE);
                view.addObject(GenericFinal.MESSAGE,GenericFinalMSG.SUCCESS_LOGIN_MSG);
                result.setMessage(GenericFinalMSG.SUCCESS_LOGIN_MSG);
                result.setResultCode(GenericFinalMSG.SUCCESS_CODE);
            } else {
                token.clear();
               //view.setViewName("redirect:/rest/login/initLoginPage");
                view.addObject(GenericFinal.RESULTCODE,GenericFinalMSG.FAILED_CODE);
                view.addObject(GenericFinal.MESSAGE,GenericFinalMSG.FAILED_LOGIN_MSG);
                result.setMessage(GenericFinalMSG.FAILED_LOGIN_MSG);
                result.setResultCode(GenericFinalMSG.FAILED_CODE);
            }
        } catch(UnknownAccountException uae){
            UnitedLogger.error(uae);
            System.out.println("对用户[" + userInfo.getUserName() + "]进行登录验证..验证未通过,未知账户");
            //view.setViewName("redirect:/rest/login/initLoginPage");
            view.addObject(GenericFinal.RESULTCODE,GenericFinalMSG.FAILED_CODE);
            view.addObject(GenericFinal.MESSAGE,GenericFinalMSG.FAILED_LOGIN_UNKNOW_MSG);
            result.setMessage(GenericFinalMSG.FAILED_LOGIN_UNKNOW_MSG);
            result.setResultCode(GenericFinalMSG.FAILED_CODE);
        } catch(LockedAccountException lae){
            UnitedLogger.error(lae);
            System.out.println("对用户[" + userInfo.getUserName() + "]进行登录验证..验证未通过,账户已锁定");
            //view.setViewName("redirect:/rest/login/initLoginPage");
            view.addObject(GenericFinal.RESULTCODE,GenericFinalMSG.FAILED_CODE);
            view.addObject(GenericFinal.MESSAGE,GenericFinalMSG.FAILED_LOGIN_LOCK_MSG);
            result.setMessage(GenericFinalMSG.FAILED_LOGIN_LOCK_MSG);
            result.setResultCode(GenericFinalMSG.FAILED_CODE);
        } catch(ExcessiveAttemptsException eae){
            UnitedLogger.error(eae);
            System.out.println("对用户[" + userInfo.getUserName() + "]进行登录验证..验证未通过,错误次数过多");

            //view.setViewName("redirect:/rest/login/initLoginPage");
            view.addObject(GenericFinal.RESULTCODE,GenericFinalMSG.FAILED_CODE);
            view.addObject(GenericFinal.MESSAGE,GenericFinalMSG.FAILED_LOGIN_ENTERERROR_MSG);
            result.setMessage(GenericFinalMSG.FAILED_LOGIN_ENTERERROR_MSG);
            result.setResultCode(GenericFinalMSG.FAILED_CODE);
        } catch(AuthenticationException ae){
            UnitedLogger.error(ae);
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            System.out.println("对用户[" + userInfo.getUserName() + "]进行登录验证..验证未通过,堆栈轨迹如下");
            //view.setViewName("redirect:/rest/login/initLoginPage");
            view.addObject(GenericFinal.RESULTCODE,GenericFinalMSG.FAILED_CODE);
            view.addObject(GenericFinal.MESSAGE,GenericFinalMSG.FAILED_LOGIN_MSG);
            result.setMessage(GenericFinalMSG.FAILED_LOGIN_MSG);
            result.setResultCode(GenericFinalMSG.FAILED_CODE);
            //ae.printStackTrace();
        }  catch (Exception e) {
            UnitedLogger.error(e);
            //view.setViewName("redirect:/rest/login/initLoginPage");
            view.addObject(GenericFinal.RESULTCODE,GenericFinalMSG.FAILED_CODE);
            view.addObject(GenericFinal.MESSAGE,GenericFinalMSG.FAILED_UNKNOW_MSG);
            result.setMessage(GenericFinalMSG.SUCCESS_LOGIN_MSG);
            result.setResultCode(GenericFinalMSG.FAILED_CODE);
        }
        return result;
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String logout(){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        return "redirect:/rest/login/initLoginPage";
    }

    /**
     * 没有权限
     * @return
     */
    @RequestMapping("/403")
    public String unauthorizedRole(){
        return "/403";
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
     * 服务器内部错误
     * @return
     */
    @RequestMapping("/500")
    public String serviceInternalError(){
        return "/500";
    }


}
