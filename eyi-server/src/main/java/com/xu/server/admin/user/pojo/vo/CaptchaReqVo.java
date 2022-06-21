package com.xu.server.admin.user.pojo.vo;

import com.xu.server.admin.user.constant.CaptchaType;
import lombok.Data;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/21 16:29
 */
@Data
public class CaptchaReqVo {
    private CaptchaType type;
	private String email;
}
