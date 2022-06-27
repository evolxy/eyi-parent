package com.xu.server.base.enums;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/17 15:36
 */

public enum DelFlagEnum implements BaseEnum<DelFlagEnum, Integer>{
	/**
	 * 0 未删除 1 删除
	 */
	DELETED( 1, "已删除"), NOT_DELETED(0, "未删除");

	DelFlagEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	private final int value;
	private final String name;

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public String getName() {
		return name;
	}
}
