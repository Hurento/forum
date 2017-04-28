package com.sy.forum.web.startup.conf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author SY
 * @ClassName IndexListener
 * @Description:
 * @Date 2017-04-21 10:17
 */
//@WebListener
public class IndexListener implements ServletContextListener {
    private Log log = LogFactory.getLog(IndexListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("IndexListener contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}