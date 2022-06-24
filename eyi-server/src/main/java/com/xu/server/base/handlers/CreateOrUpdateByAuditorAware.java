package com.xu.server.base.handlers;

import com.xu.server.base.util.EyiLoginUserUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/23 10:34
 */

public class CreateOrUpdateByAuditorAware implements AuditorAware<Long> {
	@Override
	public Optional<Long> getCurrentAuditor() {
		Long userId = EyiLoginUserUtil.loginUserId();
		return Optional.of(Objects.requireNonNullElse(userId, 1231L));
	}
}
