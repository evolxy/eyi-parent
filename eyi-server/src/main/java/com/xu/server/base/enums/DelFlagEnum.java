package com.xu.server.base.enums;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/17 15:36
 */

public enum DelFlagEnum {
	/**
	 * 0 未删除 1 删除
	 */
	DELETED( 1), NOT_DELETED(0);

	DelFlagEnum(int value) {
		this.value = value;
	}

	private final int value;

	public int getValue() {
		return value;
	}
}
