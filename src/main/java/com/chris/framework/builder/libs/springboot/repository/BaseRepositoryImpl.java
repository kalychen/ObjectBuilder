package com.chris.framework.builder.libs.springboot.repository;


import com.chris.framework.builder.model.PageModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

/**
 * CustomRepositoryDemo
 * library.custom
 * Created by Chris Chen
 * 2017/9/15
 * Explain:
 */
public class BaseRepositoryImpl<T>
        extends SimpleJpaRepository<T, Integer>
        implements BaseRepository<T> {

    private EntityManager entityManager;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Integer getLastInsertId() {
        Object result = entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();
        return Integer.valueOf(String.valueOf(result));
    }

    /**
     * 获取自定义分页
     *
     * @param pageable
     * @return
     */
    @Override
    public PageModel<T> getPage(Pageable pageable) {
        PageModel<T> pageModel = new PageModel<T>(pageable.getPageNumber() + 1, pageable.getPageSize());
        pageModel.count = count();
        pageModel.hasNext = pageModel.page * pageModel.pageSize < pageModel.count;//是否有下一页
        pageModel.dataList = findAll(pageable).getContent();
        return pageModel;
    }
}
