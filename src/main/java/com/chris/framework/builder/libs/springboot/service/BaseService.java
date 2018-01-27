package com.chris.framework.builder.libs.springboot.service;


import com.chris.framework.builder.annotation.Provider;
import com.chris.framework.builder.libs.springboot.repository.BaseRepository;
import com.chris.framework.builder.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;

/**
 * CustomRepositoryDemo
 * library.custom
 * Created by Chris Chen
 * 2017/9/15
 * Explain:封装最基本的Service
 */
public abstract class BaseService<T, R extends BaseRepository<T>> {
    @Autowired
    public R dao;

    //1. 查询:一条数据
    //@Provider
    public T getOne(Integer id) {
        return id == null ? null : dao.findOne(id);
    }

    //2. 查询：数据列表 按照id升序排列
    //@Provider
    public List<T> getList() {
        return dao.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    //3. 查询：数据分页 按照id升序排列
    //@Provider
    public PageModel<T> getPage(int page, int pageSize) {
        if (page <= 0 || pageSize <= 0) {
            return null;
        }
        //数据库分页查询起始id是从0开始的，请求的页码是从1开始的，所以处理的时候要减一
        return dao.getPage(new PageRequest(page > 0 ? page - 1 : 0, pageSize, new Sort(Sort.Direction.ASC, "id")));
    }

    //4. 增加：增加一条数据
    @Transactional
    public Integer addOne(T t) {
        try {
            //查找是否包含一个叫"id"的字段，有这个字段我们就把它视作主键
            Field indexField = t.getClass().getDeclaredField("id");
            if (indexField != null) {
                indexField.setAccessible(true);
                indexField.set(t, 0);//主键清零
                indexField.setAccessible(false);
            }
            dao.saveAndFlush(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dao.getLastInsertId();
    }

    /**
     * 留给chris框架保存数据的默认方法
     *
     * @param t
     * @return
     */
    @Transactional
    public Integer save(T t) {
        return addOne(t);
    }

    //5. 删除：删除一条记录
    @Transactional
    public boolean removeOne(Integer id) {
        try {
            dao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //6. 修改：修改一条记录 对象必须包含id
    @Transactional
    public boolean updateOne(T t) {
        try {
            dao.saveAndFlush(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public abstract T getEntity(Integer id);//获取一个对象

    public abstract List<T> getAll();//获取所有对象的集合
}
