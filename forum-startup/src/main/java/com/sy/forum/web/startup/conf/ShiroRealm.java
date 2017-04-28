package com.sy.forum.web.startup.conf;

import com.sy.forum.system.users.service.UserService;
import com.sy.forum.utils.Utils;
import com.sy.forum.system.users.model.UserInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
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
        System.out.println("登录认证");
        //查出是否有此用户
        UserInfo user = new UserInfo();
        user.setLoginName(username);
        user.setLoginPassword(password);
        user = userService.getUserInfoByUsernameAndPassword(user);
        if(!Utils.isEmpty(user)){
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            //return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }
        return null;
    }
}
