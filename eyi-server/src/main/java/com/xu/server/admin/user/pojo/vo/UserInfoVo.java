package com.xu.server.admin.user.pojo.vo;

import com.xu.server.base.enums.GenderEnum;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/21 15:07
 */
@Data
public class UserInfoVo {
	private String introduce;

	private LocalDate birthday;

	private String nickname;

	private GenderEnum gender;
}
