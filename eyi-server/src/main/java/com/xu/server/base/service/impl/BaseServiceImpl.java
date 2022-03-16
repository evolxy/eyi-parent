package com.xu.server.base.service.impl;

import com.xu.server.base.service.IBaseService;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 16:52
 */
@Service
public class BaseServiceImpl<T, ID, M extends JpaRepository<T, ID>> implements IBaseService<T> {
    private final M repository;

    public BaseServiceImpl(M repository) {
        this.repository = repository;
    }

    @Override
    public List<T> list() {
        return repository.findAll();
    }

    @Override
    public List<T> list(Criteria criteria) {
        return repository.findAll();
    }

    @Override
    public Page<T> page(Pageable pageable) {
        return null;
    }

    @Override
    public Page<T> page(Pageable pageable, Criteria criteria) {
        return null;
    }
}
