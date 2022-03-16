package com.xu.server.base.service;

import org.hibernate.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 9:56
 */

public interface IBaseService<T> {
    List<T> list();

    List<T> list(Criteria criteria);

    Page<T> page(Pageable pageable);

    Page<T> page(Pageable pageable, Criteria criteria);
}
