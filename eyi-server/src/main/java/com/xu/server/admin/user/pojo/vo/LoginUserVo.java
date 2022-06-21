package com.xu.server.admin.user.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/28 10:35
 */
@Data
public class LoginUserVo {

	private String username;
	private String password;

	@NotNull
	private String codeId;
	@NotNull
	private String code;

	private String email;
}
