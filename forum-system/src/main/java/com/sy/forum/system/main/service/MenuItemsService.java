package com.sy.forum.system.main.service;

import com.sy.forum.generic.GenericService;
import com.sy.forum.system.main.model.MenuItems;

import java.util.List;

/**
 * @Author SY
 * @ClassName MenuItemsService
 * @Description: 菜单业务接口
 * @Date 2017-04-24 10:38
 */
public interface MenuItemsService extends GenericService<MenuItems, String> {
    /**
     * 查询菜单
     * @param item
     * @return
     */
    List<MenuItems> findMenuItemsList(MenuItems item);
}
