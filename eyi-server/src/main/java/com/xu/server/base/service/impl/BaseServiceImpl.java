package com.xu.server.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.server.base.pojo.entity.BaseEntity;
import com.xu.server.base.service.IBaseService;
import com.xu.server.base.util.QueryWrapperCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 16:52
 */
public class BaseServiceImpl<T extends BaseEntity, M extends BaseMapper<T>> extends ServiceImpl< M, T> implements IBaseService<T> {
	@Override
	public List<T> list(T entity) {
		QueryWrapper<T> wrapper = QueryWrapperCreator.create(entity);
		if (wrapper == null) {
			return new ArrayList<>();
		}
		return baseMapper.selectList(wrapper);
	}

	@Override
	public Page<T> page(int current, int size, T entity) {
		Page<T> page = new Page<>(current, size);
		QueryWrapper<T> wrapper = QueryWrapperCreator.create(entity);
		return baseMapper.selectPage(page, wrapper);
	}

	@Override
	public Page<T> page(int current, int size) {
		Page<T> page = new Page<>(current, size);
		return baseMapper.selectPage(page, new QueryWrapper<>());
	}
}
