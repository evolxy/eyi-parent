package com.xu.server.base.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/27 16:31
 */

@Slf4j
public class QueryWrapperCreator {
	public static <T> QueryWrapper<T> create(T entity) {
		QueryWrapper<T> qw = new QueryWrapper<>();
		Field[] fields = BeanPropsUtils.getFields(entity.getClass());
		for (Field field : fields) {
			field.setAccessible(true);
			if (BeanPropsUtils.isFinalProp(field)) {
				continue;
			}
			try {
				String name = field.getName();
				Object value = field.get(entity);
				if (value!=null) {
					qw.eq(FieldNameUtil.humpToLine(name), value);
				}
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
				return null;
			}
		}
		return qw;
	}
}
