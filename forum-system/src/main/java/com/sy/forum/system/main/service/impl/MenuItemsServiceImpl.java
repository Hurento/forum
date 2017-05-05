package com.sy.forum.system.main.service.impl;

import com.sy.forum.exceptions.UnitedException;
import com.sy.forum.generic.GenericDao;
import com.sy.forum.generic.GenericServiceImpl;
import com.sy.forum.system.main.dao.MenuItemsDao;
import com.sy.forum.system.main.model.MenuItems;
import com.sy.forum.system.main.service.MenuItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author SY
 * @ClassName MenuItemsServiceImpl
 * @Description: 菜单业务实现
 * @Date 2017-05-05 14:01
 */
@Service
public class MenuItemsServiceImpl extends GenericServiceImpl<MenuItems,String> implements MenuItemsService {

    @Autowired
    private MenuItemsDao menuItemsDao;

    @Override
    public GenericDao<MenuItems, String> getDao() {
        return menuItemsDao;
    }

    @Override
    public int insert(MenuItems menuItems) throws UnitedException {
        return super.insert(menuItems);
    }

    @Override
    public int update(MenuItems menuItems) throws UnitedException {
        return super.update(menuItems);
    }

    @Override
    public int delete(String id) throws UnitedException {
        return super.delete(id);
    }

    @Override
    public MenuItems selectById(String id) throws UnitedException {
        return super.selectById(id);
    }

    @Override
    public List<MenuItems> findMenuItemsList(MenuItems item) {

        return menuItemsDao.findMenuItemsList(item);
    }

}
