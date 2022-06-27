package com.xu.server.base.enums;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/27 16:51
 */

public interface BaseEnum<E extends Enum<?>, T> {
	/**
	 * 获取值
	 * @return value
	 */
	T getValue();

	/**
	 * 获取描述
	 * @return name
	 */
	String getName();
}
