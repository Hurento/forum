package com.sy.forum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author SY
 * @ClassName ApplacationMain
 * @Description: SpringBoot 启动应用
 * @Date 2017-04-20 9:57
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.sy.forum.*")
@ComponentScan(basePackages = "com.sy.forum.*")
@MapperScan(basePackages = "com.sy.forum.*.*.dao")
@EnableAutoConfiguration
public class ApplacationMain{
    public static void main(String []args) {
        SpringApplication.run(ApplacationMain.class,args);
    }

}
