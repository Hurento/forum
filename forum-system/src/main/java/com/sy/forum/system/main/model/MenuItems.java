package com.sy.forum.system.main.model;

/**
 * @Author SY
 * @ClassName MenuItems
 * @Description: 菜单
 * @Date 2017-05-05 13:53
 */
public class MenuItems {
    private String menuItemId;// 菜单id
    private String parentMenuId;// 父级菜单id
    private String menuKey;// 菜单键
    private String menuValue;// 菜单值
    private String menuShowType;// 菜单显示类型（group-menu、tree-menu、single-menu）
    private String vcUrl;// 访问路径
    private String vcIcon;// 显示图标
    private String vcStatus;// 状态
    private String menuDescribe;// 菜单描述

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getMenuValue() {
        return menuValue;
    }

    public void setMenuValue(String menuValue) {
        this.menuValue = menuValue;
    }

    public String getMenuShowType() {
        return menuShowType;
    }

    public void setMenuShowType(String menuShowType) {
        this.menuShowType = menuShowType;
    }

    public String getVcUrl() {
        return vcUrl;
    }

    public void setVcUrl(String vcUrl) {
        this.vcUrl = vcUrl;
    }

    public String getVcIcon() {
        return vcIcon;
    }

    public void setVcIcon(String vcIcon) {
        this.vcIcon = vcIcon;
    }

    public String getVcStatus() {
        return vcStatus;
    }

    public void setVcStatus(String vcStatus) {
        this.vcStatus = vcStatus;
    }

    public String getMenuDescribe() {
        return menuDescribe;
    }

    public void setMenuDescribe(String menuDescribe) {
        this.menuDescribe = menuDescribe;
    }
}
