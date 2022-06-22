package com.xu.server.base.util;

import cn.dev33.satoken.stp.StpUtil;
import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.repository.UserInfoRepository;
import com.xu.server.base.pojo.bo.LoginUserBo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/28 16:51
 */
@Slf4j
public class EyiLoginUserUtil {
	public static LoginUserBo loginUser() {
		Long loginUserId = null;
		String tokenValue = StpUtil.getTokenValue();
		if (StringUtils.isNotBlank(tokenValue)) {
			Object loginIdByToken = StpUtil.getLoginIdByToken(tokenValue);
			if (loginIdByToken != null) {
				try {
					loginUserId = Long.parseLong(loginIdByToken.toString());
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					return null;
				}
			}
		}
		if (StpUtil.isLogin()) {
			loginUserId = StpUtil.getLoginIdAsLong();
		}
		if (loginUserId != null) {
			UserInfoRepository repository = ApplicationContextUtil.getBean(UserInfoRepository.class);
			Optional<EyiUser> optional = repository.findById(loginUserId);
			if (optional.isPresent()) {
				EyiUser user = optional.get();
				LoginUserBo bo = new LoginUserBo();
				BeanUtils.copyProperties(user, bo);
				return bo;
			}
		}
		return null;
	}
}
