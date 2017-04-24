package com.sy.forum.dao;

import com.sy.forum.entity.SysVisitPermissionInit;

import java.util.List;

/**
 * @Author SY
 * @ClassName SysPermissionInitDao
 * @Description: 访问权限数据层接口
 * @Date 2017-04-24 10:42
 */
public interface SysPermissionInitDao {
    /**
     * 查询权限配置
     * @return
     */
    List<SysVisitPermissionInit> findSysVisitPermissionInitList();
}
