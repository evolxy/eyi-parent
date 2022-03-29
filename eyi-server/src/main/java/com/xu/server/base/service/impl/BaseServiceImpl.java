package com.xu.server.base.service.impl;

import com.xu.server.base.repository.BaseRepository;
import com.xu.server.base.service.IBaseService;
import com.xu.server.base.util.QueryBuilderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 16:52
 */
public class BaseServiceImpl<T, M extends BaseRepository<T>> implements IBaseService<T> {
    @Autowired
    protected M repository;

    @Override
    public List<T> list() {
        return repository.findAll();
    }

    @Override
    public List<T> list(Specification<T> criteria) {
        return repository.findAll(criteria);
    }

    @Override
    public Page<T> page(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<T> page(int pageNo, int pageSize) {
        return repository.findAll(PageRequest.of(pageNo, pageSize));
    }

    @Override
    public Page<T> page(int pageNo, int pageSize, Sort sort) {
        return repository.findAll(PageRequest.of(pageNo, pageSize, sort));
    }

    @Override
    public Page<T> page(int pageNo, int pageSize, T entity) {
        if (entity!=null) {
            return repository.findAll(QueryBuilderUtil.specificationBuild(entity), PageRequest.of(pageNo, pageSize));
        } else {
            return repository.findAll(PageRequest.of(pageNo, pageSize));
        }
    }

    @Override
    public T getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public T saveOrUpdate(T entity) {
        return repository.save(entity);
    }

    @Override
    public boolean saveBatch(List<T> entities) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(List<T> entities) {
        return false;
    }

    @Override
    public boolean removeById(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean remove(Specification<T> specification) {
        return false;
    }

    @Override
    public List<T> list(T entity) {
        if (entity!=null) {
            return repository.findAll(QueryBuilderUtil.specificationBuild(entity));
        } else {
            return list();
        }
    }
}
