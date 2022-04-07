package com.xu.server.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 9:56
 */

public interface IBaseService<T> {
    List<T> list();

    List<T> list(Specification<T> criteria);

    Page<T> page(Pageable pageable);

    Page<T> page(int pageNo, int pageSize) ;

    Page<T> page(int pageNo, int pageSize, Sort sort) ;

    Page<T> page(int pageNo, int pageSize, T entity) ;

    T getById(Long id);

    T saveOrUpdate(T entity);

    boolean saveBatch(List<T> entities);

    boolean saveOrUpdateBatch(List<T> entities);

    boolean removeById(Long id);

    boolean isExisted(Long id);

    boolean isValid(Long id);

    int logicRemoveById(Long id);

    boolean logicRemove(Specification<T> specification);

    boolean remove(Specification<T> specification);

    List<T> list(T entity);

}
