package com.sy.forum.web.startup.conf;

import com.sy.forum.core.entity.GenericFinal;
import com.sy.forum.core.entity.GenericFinalMSG;
import com.sy.forum.system.users.model.UserInfo;
import com.sy.forum.system.users.service.UserService;
import com.sy.forum.utils.Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author SY
 * @ClassName ShiroRealm
 * @Description: 实现登录认证和授权
 * @Date 2017-04-23 23:39
 */
@ComponentScan
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("角色认证");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection);
        //到数据库查是否有此对象
        UserInfo user = new UserInfo();
        user.setLoginName(loginName);
        user = userService.getUserInfoByUsernameAndPassword(user);
        if(!Utils.isEmpty(user)){
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
//            info.setRoles(user.getRolesName());
//            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
//            List<Role> roleList=user.getRoleList();
//            for (Role role : roleList) {
//                info.addStringPermissions(role.getPermissionsName());
//            }
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        System.out.println("开始登录认证.....");
        //查出是否有此用户
        UserInfo user = new UserInfo();
        user.setLoginName(username);
        user.setLoginPassword(password);
        user = userService.getUserInfoByUsernameAndPassword(user);
        if(!Utils.isEmpty(user)){
            // 验证用户是否被禁用
            if (GenericFinal.NUMBER_ONE.equals(user.getCureentStatus()))
                throw new LockedAccountException(GenericFinalMSG.FAILED_LOGIN_DISABLE_MSG);
            // 验证是否锁定,已被锁定用户不能成功登录到系统
            if (GenericFinal.NUMBER_ONE.equals(user.getLockStatus()))
                throw new LockedAccountException(GenericFinalMSG.FAILED_LOGIN_LOCK_MSG);

            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(user.getLoginName(), password, getName());
        } else {
            user = new UserInfo();
            user.setLoginName(username);
            user.setUserType(password);
            // 根据用户名查询用户
            UserInfo isUser = userService.getUserInfoByUsernameAndPassword(user);
            // 若根据用户名查询用户不为空时，抛出异常‘密码错误’，且根据各种情况抛出异常
            if(!Utils.isEmpty(isUser)) {
                // 验证用户是否被禁用
                if (GenericFinal.NUMBER_ONE.equals(isUser.getCureentStatus()))
                    throw new LockedAccountException(GenericFinalMSG.FAILED_LOGIN_DISABLE_MSG);
                // 验证是否锁定
                if (GenericFinal.NUMBER_ONE.equals(isUser.getLockStatus()))
                    throw new LockedAccountException(GenericFinalMSG.FAILED_LOGIN_LOCK_MSG);
                // 最大错误次数
                int maxErrorCount = Integer.valueOf(GenericFinal.NUMBER_FIVE);
                // 默认错误次数
                int defaultErrorCount = Integer.valueOf(GenericFinal.NUMBER_THREE);
                // 当前错误次数
                int errorCount = Integer.valueOf(isUser.getErrorCount()) + 1;
                // 更新错误次数
                userService.updateUserErrorCountByUserId(String.valueOf(errorCount), isUser.getUserId());
                // 错误次数达到5次、锁定用户
                if(maxErrorCount == errorCount) {
                    //锁定用户
                    userService.updateUserLockStatusByUserId(isUser.getUserId());
                    throw new LockedAccountException(GenericFinalMSG.FAILED_LOGIN_LOCK_MSG);
                }
                // 验证登录错误次数,大于等于三次后提示并修改错误次数
                if (errorCount >= defaultErrorCount) {
                    throw new ExcessiveAttemptsException(GenericFinalMSG.FAILED_LOGIN_ENTERERROR_MSG.replace("COUNT", String.valueOf(maxErrorCount - errorCount)));
                }
                // 密码错误
                throw new AuthenticationException(GenericFinalMSG.FAILED_LOGIN_PASSWORD_MSG);
            } else {
                throw new UnknownAccountException(GenericFinalMSG.FAILED_LOGIN_UNKNOW_MSG);
            }
        }
    }
}
