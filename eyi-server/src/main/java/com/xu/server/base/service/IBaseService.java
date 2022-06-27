package com.xu.server.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.server.base.pojo.entity.BaseEntity;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 9:56
 */

public interface IBaseService<T extends BaseEntity> extends IService<T> {

	/**
	 * 查询
	 *
	 * @param entity 条件
	 * @return list
	 */
	List<T> list(T entity);

	Page<T> page(int current, int size, T entity);

	Page<T> page(int current, int size);
}
