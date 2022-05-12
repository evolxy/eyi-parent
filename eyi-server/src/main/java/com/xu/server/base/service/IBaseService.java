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
    /**
     * 列表
     * @return list
     */
    List<T> list();

    /**
     * 条件列表
     * @param criteria 条件
     * @return list
     */
    List<T> list(Specification<T> criteria);

    /**
     * 分页
     * @param pageable 分页参数
     * @return page
     */
    Page<T> page(Pageable pageable);

    /**
     * 分页
     * @param pageNo 页数
     * @param pageSize 条数
     * @return page
     */
    Page<T> page(int pageNo, int pageSize) ;

    /**
     * 排序分页
     * @param pageNo 页数
     * @param pageSize 条数
     * @param sort 排序
     * @return page
     */
    Page<T> page(int pageNo, int pageSize, Sort sort) ;

    /**
     * 排序分页
     * @param pageNo 页数
     * @param pageSize 条数
     * @param entity 查询条件
     * @return page
     */
    Page<T> page(int pageNo, int pageSize, T entity) ;

    /**
     * getById
     * @param id id
     * @return T
     */
    T getById(Long id);

    /**
     * 更新或保存
     * @param entity T
     * @return T
     */
    T saveOrUpdate(T entity);

    /**
     * 批量保存
     * @param entities list
     * @return true|false
     */
    boolean saveBatch(List<T> entities);

    /**
     * 批量保存
     * @param entities list
     * @return true|false
     */
    boolean saveOrUpdateBatch(List<T> entities);

    /**
     * 物理删除
     * @param id id
     * @return true|false
     */
    boolean removeById(Long id);

    /**
     * 数据是否存在
     * @param id id
     * @return true|false
     */
    boolean isExisted(Long id);

    /**
     * 数据是否有效
     * @param id id
     * @return true|false
     */
    boolean isValid(Long id);

    /**
     * 逻辑删除
     * @param id id
     * @return true|false
     */
    int logicRemoveById(Long id);
    /**
     * 条件逻辑删除
     * @param specification 条件
     * @return true|false
     */
    boolean logicRemove(Specification<T> specification);

    /**
     * 条件删除
     * @param specification 条件
     * @return true|false
     */
    boolean remove(Specification<T> specification);

    /**
     * 列表查询
     * @param entity 条件
     * @return list
     */
    List<T> list(T entity);

}
