package com.sy.forum.service;

import com.sy.forum.entity.SysVisitPermissionInit;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * @Author SY
 * @ClassName SysPermissionInitService
 * @Description: 访问权限业务接口
 * @Date 2017-04-24 10:38
 */
@ComponentScan
public interface SysPermissionInitService {

    /**
     * 查询权限配置
     * @return
     */
    List<SysVisitPermissionInit> findSysVisitPermissionInitList();
}
