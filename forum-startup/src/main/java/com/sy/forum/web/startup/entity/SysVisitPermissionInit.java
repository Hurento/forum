package com.sy.forum.web.startup.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @Author SY
 * @ClassName SysVisitPermissionInit
 * @Description: 系统权限初始化
 * @Date 2017-04-24 10:20
 */
@EntityScan
public class SysVisitPermissionInit {

    private String visitPaht;// 访问路径
    private String permissionInit;// 权限

    public SysVisitPermissionInit() {}

    public SysVisitPermissionInit(String visitPaht, String permissionInit) {
        this.visitPaht = visitPaht;
        this.permissionInit = permissionInit;
    }

    public String getPermissionInit() {
        return permissionInit;
    }

    public void setPermissionInit(String permissionInit) {
        this.permissionInit = permissionInit;
    }

    public String getVisitPaht() {
        return visitPaht;
    }

    public void setVisitPaht(String visitPaht) {
        this.visitPaht = visitPaht;
    }
}
