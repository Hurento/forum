package com.sy.forum.web.startup.conf;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeUnit;

/**
 * @Author SY
 * @ClassName CustomServletContainer
 * @Description:
 * @Date 2017-04-21 10:29
 */
@ComponentScan
public class CustomServletContainer implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/400.html"));
        container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED,"/401.html"));
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"));
        container.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED,"/405.html"));
        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500.html"));
        container.setSessionTimeout(10, TimeUnit.MINUTES);
    }
}