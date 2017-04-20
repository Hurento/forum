package com.sy.forum.generic;

import com.sy.forum.exceptions.UnitedException;

/**
 * @Author SY
 * @Description: 所有自定义Service的顶级接口, 封装常用的增删查改操作
 * Model : 代表数据库中的表 映射的Java对象类型
 * PK :代表对象的主键类型
 * @Date 2017/3/13 17:26
 */
public interface GenericService<Model, PK> {

    /**
     * 插入对象
     *
     * @param model 对象
     */
    int insert(Model model) throws UnitedException;

    /**
     * 更新对象
     *
     * @param model 对象
     */
    int update(Model model) throws UnitedException;

    /**
     * 通过主键, 删除对象
     *
     * @param id 主键
     */
    int delete(PK id) throws UnitedException;

    /**
     * 通过主键, 查询对象
     *
     * @param id 主键
     * @return model 对象
     */
    Model selectById(PK id) throws UnitedException;
}
