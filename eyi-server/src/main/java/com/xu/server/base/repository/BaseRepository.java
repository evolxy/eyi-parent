package com.xu.server.base.repository;

import org.hibernate.annotations.Where;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/21 13:46
 */
@NoRepositoryBean
@Where(clause = "del_flag = 0")
public interface BaseRepository<T> extends JpaRepository<T, Long> {
    String baseQuerySql = "SELECT t FROM #{#entityName} t where t.delFlag = 0 ";
    @Query(baseQuerySql + "and id=?1")
    @Transactional(readOnly = true)
    @Override
    Optional<T> findById(@Nonnull Long id);

    @Query(baseQuerySql)
    @Transactional(readOnly = true)
    List<T> findAll(Specification<T> specification);

    @Query(baseQuerySql)
    @Transactional(readOnly = true)
    Page<T> findAll(Specification<T> specification, Pageable page);

    @Query(baseQuerySql)
    @Transactional(readOnly = true)
    @Override
    Page<T> findAll(@Nonnull Pageable page);


    @Query(baseQuerySql)
    @Transactional(readOnly = true)
    @Override
    List<T> findAll(@Nonnull Sort sort);

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} t SET t.delFlag = 1 WHERE t.delFlag = 0 and t.id = ?1")
    int logicDelById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} t SET t.delFlag = 1 WHERE t.delFlag = 0 and t.id in ?1")
    boolean logicDelByIds(List<Long> ids);
}
