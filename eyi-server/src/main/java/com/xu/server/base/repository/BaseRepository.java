package com.xu.server.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/21 13:46
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {
    List<T> findAll(Specification<T> specification);

    Page<T> findAll(Specification<T> specification, Pageable page);

}
