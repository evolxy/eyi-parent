package com.xu.server.admin.user.constant;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/21 16:02
 */

public enum CaptchaType {
	/**
	 * 验证码类型
	 */
    WEB(1), EMAIL(2);

	private final int value;

	CaptchaType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
