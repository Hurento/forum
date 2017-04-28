package com.sy.forum.web.startup.conf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author SY
 * @ClassName IndexFilter
 * @Description:
 * @Date 2017-04-21 10:18
 */
//@WebFilter(urlPatterns = "/*", filterName = "indexFilter")
public class IndexFilter implements Filter {
    Log log = LogFactory.getLog(IndexFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init IndexFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter IndexFilter");
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
