package com.sy.forum.conf;

import com.sy.forum.system.users.model.UserInfo;
import com.sy.forum.utils.Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author SY
 * @ClassName ShiroRealm
 * @Description: 实现登录认证和授权
 * @Date 2017-04-23 23:39
 */
@ComponentScan
public class ShiroRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("角色认证");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection);
        //到数据库查是否有此对象
        UserInfo user = new UserInfo();//
        // userDao.findByName(loginName);// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
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
        System.out.println("登录认证");
        //查出是否有此用户
        UserInfo user = new UserInfo();//
        if(!Utils.isEmpty(user)){
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            //return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }
        return null;
    }
}
