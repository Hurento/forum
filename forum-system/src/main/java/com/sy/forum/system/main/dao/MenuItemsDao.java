package com.sy.forum.system.main.dao;

import com.sy.forum.generic.GenericDao;
import com.sy.forum.system.main.model.MenuItems;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Author SY
 * @ClassName MenuItemsDao
 * @Description: 菜单数据层接口
 * @Date 2017-04-24 10:42
 */
@Repository
public interface MenuItemsDao extends GenericDao<MenuItems, String> {

    /**
     * 查询菜单
     * @param item
     * @return
     */
    List<MenuItems> findMenuItemsList(MenuItems item);
}
