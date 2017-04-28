package com.sy.forum.web.startup.conf;

import com.sy.forum.core.entity.FileExtensionFinal;
import com.sy.forum.utils.Utils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

/**
 * @Author SY
 * @ClassName REShiroFilterFactoryBean
 * @Description: 继承 ShiroFilterFactoryBean 处理拦截资源文件问题。
 * @Date 2017-04-24 0:15
 */
@ComponentScan
public class REShiroFilterFactoryBean extends ShiroFilterFactoryBean {
    // 对ShiroFilter来说，需要直接忽略的请求
    private Set<String> ignoreExt;

    public REShiroFilterFactoryBean() {
        super();
        ignoreExt = FileExtensionFinal.getIgnoreExt();
    }


    @Override
    protected AbstractShiroFilter createInstance() throws Exception {

        SecurityManager securityManager = getSecurityManager();
        if (Utils.isEmpty(securityManager)) {
            String msg = "SecurityManager property must be set.";
            throw new BeanInitializationException(msg);
        }

        if (!(securityManager instanceof WebSecurityManager)) {
            String msg = "The security manager does not implement the WebSecurityManager interface.";
            throw new BeanInitializationException(msg);
        }

        FilterChainManager manager = createFilterChainManager();

        PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
        chainResolver.setFilterChainManager(manager);

        return new MSpringShiroFilter((WebSecurityManager) securityManager, chainResolver);
    }

    private final class MSpringShiroFilter extends AbstractShiroFilter {

        protected MSpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {
            super();
            if (webSecurityManager == null) {
                throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
            }
            setSecurityManager(webSecurityManager);
            if (resolver != null) {
                setFilterChainResolver(resolver);
            }
        }

        @Override
        protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse,
                                        final FilterChain chain) throws ServletException, IOException {
            HttpServletRequest request = (HttpServletRequest)servletRequest;
            String path = request.getRequestURI().toLowerCase();

            boolean flag = true;
            int idx = 0;
            //拦截资源文件
            if(( idx = path.lastIndexOf(".")) > 0){
                path = path.substring(idx);
                if(ignoreExt.contains(path.toLowerCase())) {
                    flag = false;
                }
            }
            if(flag){
                super.doFilterInternal(servletRequest, servletResponse, chain);
            }else{
                chain.doFilter(servletRequest, servletResponse);
            }
        }

    }
}
