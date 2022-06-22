package com.xu.server.base.service.impl;

import com.xu.server.base.pojo.entity.BaseEntity;
import com.xu.server.base.repository.BaseRepository;
import com.xu.server.base.service.IBaseService;
import com.xu.server.base.util.QueryBuilderUtil;
import org.springframework.beans.BeanUtils;
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
public class BaseServiceImpl<T extends BaseEntity, M extends BaseRepository<T>> implements IBaseService<T> {
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
        return repository.findAll(PageRequest.of(pageNo-1, pageSize));
    }

    @Override
    public Page<T> page(int pageNo, int pageSize, Sort sort) {
        return repository.findAll(PageRequest.of(pageNo-1, pageSize, sort));
    }

    @Override
    public Page<T> page(int pageNo, int pageSize, T entity) {
        if (entity!=null) {
            entity.deleteBaseProps();
            Specification<T> specification = QueryBuilderUtil.specificationBuild(entity);
            return repository.findAll(specification, PageRequest.of(pageNo-1, pageSize));
        } else {
            return repository.findAll(PageRequest.of(pageNo-1, pageSize));
        }
    }

    @Override
    public T getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public T saveOrUpdate(T entity) {
        T db = getById(entity.getId());
        entity.setCreateTime(null);
        BeanUtils.copyProperties(entity, db);
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
    public boolean isExisted(Long id) {
        // 存在 -> true 否则为false
        return repository.findById(id).isEmpty();
    }

    @Override
    public boolean isValid(Long id) {
        // 存在且删除标记为0 为true  否则为false
        T entity = repository.findById(id).orElse(null);
        return entity != null && entity.getDelFlag() == 0;
    }

    @Override
    public int logicRemoveById(Long id) {
        return repository.logicDelById(id);
    }

    @Override
    public boolean logicRemove(Specification<T> specification) {
        return false;
    }

    @Override
    public boolean remove(Specification<T> specification) {
        return false;
    }

    @Override
    public List<T> list(T entity) {
        if (entity!=null) {
            entity.deleteBaseProps();
            return repository.findAll(QueryBuilderUtil.specificationBuild(entity));
        } else {
            return list();
        }
    }
}
