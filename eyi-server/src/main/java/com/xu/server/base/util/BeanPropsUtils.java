package com.xu.server.base.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/23 11:27
 */

@Slf4j
public class BeanPropsUtils {
	/**
	 * 拷贝 同名非null，非final static属性
 	 */
	public static void copyNotNullProps(Object source, Object target) {
		Class<?> sourceClazz = source.getClass();
		Class<?> targetClazz = target.getClass();
		// 获取所有属性
		Field[] sourceFields = getFields(sourceClazz);
		Field[] targetFields = getFields(targetClazz);
		Map<String, Field> targetFieldMap = Arrays.stream(targetFields).filter(it->!isFinalProp(it)).collect(Collectors.toMap(Field::getName, it -> it));
		for (Field field : sourceFields) {
			field.setAccessible(true);
			try {
				if (isFinalProp(field)) {
					continue;
				}
				String name = field.getName();
				Object value = field.get(source);
				Field tField = targetFieldMap.get(name);
				if (tField == null) {
					continue;
				}
				if (field.getType() != tField.getType()) {
					continue;
				}
				tField.setAccessible(true);
				tField.set(target, value);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 获取父类及自身的所有属性
	 * @param clazz class
	 * @return field[]
	 */
	public static Field[] getFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<>();
		while (clazz != null) {
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		}
		return fields.toArray(new Field[0]);
	}

	public static boolean isFinalProp(Field field) {
		return Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers());
	}
}
