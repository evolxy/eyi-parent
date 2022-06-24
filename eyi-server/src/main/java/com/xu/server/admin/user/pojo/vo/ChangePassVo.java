package com.xu.server.admin.user.pojo.vo;

import lombok.Data;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/8 15:16
 */

@Data
public class ChangePassVo {
	private String oldPassword;

	private String newPassword;

	private int passwordStrength;
}
