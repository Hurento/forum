package com.sy.forum.service.impl;

import com.sy.forum.dao.SysPermissionInitDao;
import com.sy.forum.entity.SysVisitPermissionInit;
import com.sy.forum.service.SysPermissionInitService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author SY
 * @ClassName SysPermissionInitServiceImpl
 * @Description: 访问权限业务实现
 * @Date 2017-04-24 10:39
 */
@ComponentScan
@Service
public class SysPermissionInitServiceImpl implements SysPermissionInitService {

    @Resource
    SysPermissionInitDao sysPermissionInitDao;

    @Override
    public List<SysVisitPermissionInit> findSysVisitPermissionInitList() {
        return sysPermissionInitDao.findSysVisitPermissionInitList();
    }
}
