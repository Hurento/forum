package com.sy.forum.web.startup.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * @Author SY
 * @ClassName WebMVCConfig
 * @Description: 静态资源映射
 * @Date 2017-04-21 10:32
 */
@Configuration
@ComponentScan
public class WebMVCConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("classpath*:templates/**/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        return viewResolver;
    }

}