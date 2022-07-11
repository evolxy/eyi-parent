package com.xu.server.admin.user.pojo.vo;

import com.xu.server.admin.user.constant.CaptchaType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/21 16:29
 */
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaReqVo {

	public CaptchaReqVo(String email) {
		this.email = email;
	}

	private CaptchaType type;

	private String email;
	public CaptchaType getType() {
		if (this.type == null) {
			this.type = StringUtils.isBlank(email)? CaptchaType.WEB : CaptchaType.EMAIL;
		}
		return type;
	}

	public void setType(CaptchaType type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
