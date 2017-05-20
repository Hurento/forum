package com.sy.forum.system.main.service.impl;

import com.sy.forum.core.entity.UnitedLogger;
import com.sy.forum.exceptions.UnitedException;
import com.sy.forum.generic.GenericDao;
import com.sy.forum.generic.GenericServiceImpl;
import com.sy.forum.system.main.dao.MenuItemsDao;
import com.sy.forum.system.main.model.MenuItems;
import com.sy.forum.system.main.service.MenuItemsService;
import com.sy.forum.utils.LocaleUtil;
import com.sy.forum.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
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

    @Autowired
    private LocaleUtil localeUtil;

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
    public StringBuffer findMenuItemsList(MenuItems item) {
        StringBuffer htmlTemp = new StringBuffer();
        try {
            htmlTemp.append(restMenuName(menuItemsDao.findMenuItemsList(item)));
        }catch (NoSuchMessageException e) {
            UnitedLogger.error(e.getMessage(), e);
        }
        return htmlTemp;
    }

    /**
     * 国际化-菜单名称转换并创建模板
     * @param childs
     */
    public StringBuffer restMenuName(List<MenuItems> childs) {
        StringBuffer html = new StringBuffer();
        if(!Utils.isEmpty(childs)) {
            for (int j = 0; j < childs.size(); j++) {
                MenuItems item = childs.get(j);
                String key = item.getMenuKey();
                key = key.replaceAll("\\s*", "");// 去除大部分空白字符
                item.setMenuValue(localeUtil.loadLocalString(key));
                if (!Utils.isEmpty(item.getChildItems())) {
                    restMenuName(item.getChildItems());
                }
                //国际化转换后，创建菜单模板
                html.append(MenuItems.createParentMenuNode(item));
            }
        }
        return html;
    }

}
