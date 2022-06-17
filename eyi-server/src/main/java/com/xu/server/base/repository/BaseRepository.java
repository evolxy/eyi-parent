package com.xu.server.base.repository;

import com.xu.server.base.pojo.entity.BaseEntity;
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
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
    String BASE_QUERY_SQL = "SELECT t FROM #{#entityName} t where t.delFlag = 0 ";

    /**
     * 根据id查询
     * @param id id
     * @return entity
     */
    @Query(BASE_QUERY_SQL + "and id=?1")
    @Transactional(readOnly = true)
    @Override
    Optional<T> findById(@Nonnull Long id);

    /**
     * 根据条件查询
     * @param specification 封装的条件
     * @return List<entity>
     */
    @Query(BASE_QUERY_SQL)
    @Transactional(readOnly = true)
    List<T> findAll(Specification<T> specification);

    /**
     * 根据条件分页查询
     * @param specification 查询条件
     * @param page 分页参数
     * @return page
     */
    @Transactional(readOnly = true)
    Page<T> findAll(Specification<T> specification, Pageable page);


    /**
     * 分页查询
     * @param page 分页参数
     * @return page
     */
    @Query(BASE_QUERY_SQL)
    @Transactional(readOnly = true)
    @Override
    Page<T> findAll(@Nonnull Pageable page);


    /**
     * 排序查询
     * @param sort 排序条件
     * @return List
     */
    @Query(BASE_QUERY_SQL)
    @Transactional(readOnly = true)
    @Override
    List<T> findAll(@Nonnull Sort sort);

    /**
     * 逻辑删除
     * @param id id
     * @return boolean
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("UPDATE #{#entityName} t SET t.delFlag = 1 WHERE t.delFlag = 0 and t.id = ?1")
    int logicDelById(Long id);

    /**
     * 批量逻辑删除
     * @param ids ids
     * @return boolean
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("UPDATE #{#entityName} t SET t.delFlag = 1 WHERE t.delFlag = 0 and t.id in ?1")
    boolean logicDelByIds(List<Long> ids);
}
