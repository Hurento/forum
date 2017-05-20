package com.sy.forum.system.main.model;

import com.mysql.jdbc.Util;
import com.sy.forum.core.entity.GenericFinal;
import com.sy.forum.utils.Utils;

import java.math.BigDecimal;
import java.util.List;

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
    private List<MenuItems> childItems;//子菜单数据

    public MenuItems() {}

    public MenuItems(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

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

    public List<MenuItems> getChildItems() {
        return childItems;
    }

    public void setChildItems(List<MenuItems> childItems) {
        this.childItems = childItems;
    }

    /**
     * 创建菜单模板
     * @param item
     * @return
     */
    public static StringBuffer createParentMenuNode(MenuItems item) {
        StringBuffer html = new StringBuffer();
        if(GenericFinal.GROUP_MENU.equals(item.getMenuShowType())) {
            html.append("<li class=\"menu-dropdown mega-menu-dropdown\">");
        } else {
            html.append("<li class=\"menu-dropdown classic-menu-dropdown\">");
        }
        html.append("<a href=\"javascript:;\">").append(item.getMenuValue()).append("<span class=\"arrow\"></span>").append("</a>");
        //若存在子节点，则创建子元素
        if(!Utils.isEmpty(item.getChildItems())) {
            if(GenericFinal.GROUP_MENU.equals(item.getMenuShowType())) {
                html.append(createChildGroupMenu(item.getChildItems(), 2));
            } else {
                html.append(createChildMenu(item.getChildItems()));
            }
        }
        html.append("</li>");
        return html;
    }

    /**
     * 创建single、tree菜单模板
     * @param items
     * @return
     */
    public static StringBuffer createChildMenu(List<MenuItems> items) {
        StringBuffer html = new StringBuffer();
        html.append("<ul class=\"dropdown-menu pull-left\">");
        for (int i = 0; i < items.size(); i++) {
            MenuItems item = items.get(i);
            if(GenericFinal.SINGLR_MENU.equals(item.getMenuShowType())) {
                html.append("<li>");
                html.append("<a href=\"javascript:;\" class=\"nav-link\">");
            } else {
                html.append("<li class=\"dropdown-submenu\">");
                html.append("<a href=\"javascript:;\" class=\"nav-link nav-toggle\">");
            }
            html.append("<i class=\"icon-bar-chart\"></i> ").append(item.getMenuValue());
            html.append("</a>");
            if(!Utils.isEmpty(item.getChildItems())) {
                html.append(createChildMenu(item.getChildItems()));
            }
            html.append("</li>");
        }
        html.append("</ul>");
        return html;
    }

    /**
     * 创建分组菜单模板（一级）
     * @param items 需要分组的菜单
     * @param count 每组显示数量
     * @return
     */
    public static StringBuffer createChildGroupMenu(List<MenuItems> items, int count){
        List<MenuItems> groupItems = items;
        StringBuffer html = new StringBuffer();
        html.append("<ul class=\"dropdown-menu\" style=\"min-width: 710px\">");
        html.append("<li>");
        html.append("<div class=\"mega-menu-content\">");
        html.append("<div class=\"row\">");
        //计算数量
        int length = Integer.valueOf(String.valueOf(new BigDecimal(Math.floor(groupItems.size() / count))));
        for (int i = 0; i < length; i++) {
            html.append("<div class=\"col-md-4\">");
            int eachCount = 0;
            for (int j = 0; j < groupItems.size(); j++) {
                // 若遍历数据与每组显示数量一致则结束遍历，否则继续遍历并删除当前行
                if (eachCount == count)
                    break;
                MenuItems item = groupItems.get(j);
                html.append("<ul class=\"mega-menu-submenu\">");
                html.append("<li>");
                html.append("<a href=\"ui_colors.html\">").append(item.getMenuValue()).append("</a>");
                html.append("</li>");
                html.append("</ul>");
                groupItems.remove(j);
                eachCount++;
            }
            html.append("</div>");
        }
        html.append("</div>");
        html.append("</div>");
        html.append("</li>");
        html.append("</ul>");
        return html;
    }
}
