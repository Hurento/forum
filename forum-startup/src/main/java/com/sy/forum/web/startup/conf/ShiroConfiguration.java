package com.sy.forum.web.startup.conf;


import com.sy.forum.web.startup.entity.SysVisitPermissionInit;
import com.sy.forum.web.startup.service.SysPermissionInitService;
import com.sy.forum.web.startup.service.impl.SysPermissionInitServiceImpl;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author SY
 * @ClassName ShiroConfiguration
 * @Description: Shiro 配置
 * @Date 2017-04-13 23:25
 */
@ComponentScan
@Configuration
public class ShiroConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.expire}")
    private int expire;

    @Bean
    public SysPermissionInitService getSysPermissionInitService() {
        return new SysPermissionInitServiceImpl();
    }

    /**
     * shiro 缓存配置
     * @return
     */
    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }


    /**
     * 登录认证与授权缓存配置
     * @return
     */
    @Bean(name = "shiroRealm")
    public ShiroRealm getShiroRealm() {
        ShiroRealm realm = new ShiroRealm();
        realm.setCacheManager(getEhCacheManager());
        return realm;
    }

    /**
     * 配置shiro redisManager
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redis = new RedisManager();
        redis.setHost(host);
        redis.setPort(port);
        redis.setExpire(expire);// 配置过期时间
        return redis;
    }

    /**
     * cacheManager 缓存 redis实现
     * @return
     */
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     */
    public DefaultWebSessionManager SessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 生命周期处理器
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启默认的自动代理
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    /**
     * 凭证匹配器
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 加密算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 安全管理(认证与授权)
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(getShiroRealm());
        // 用户授权/认证信息Cache, 采用EhCache 缓存
        dwsm.setCacheManager(getEhCacheManager());
        return dwsm;
    }

    /**
     * 授权
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(getDefaultWebSecurityManager());
        return aasa;
    }

    /**
     * 加载shiroFilter权限控制规则
     * @param shiroFilterFactoryBean
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean){
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        SysPermissionInitService sysPermissionInitService = getSysPermissionInitService();
        //查询权限控制规则
        List<SysVisitPermissionInit> permissionInits = sysPermissionInitService.findSysVisitPermissionInitList();
        for (SysVisitPermissionInit permissionInit : permissionInits) {
            filterChainDefinitionMap.put(permissionInit.getVisitPaht(), permissionInit.getPermissionInit());
        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * shiro 过滤器
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new REShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        shiroFilterFactoryBean.setLoginUrl("/rest/login/initLoginPage");
        shiroFilterFactoryBean.setSuccessUrl("/rest/home/homePage");
        shiroFilterFactoryBean.setUnauthorizedUrl("/rest/login/403");
        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }


}
