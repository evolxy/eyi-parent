package com.xu.server.admin.user.services.impl;

import cn.dev33.satoken.stp.StpInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/12 10:24
 */
@Component
@Slf4j
public class UserAuthService implements StpInterface {
	@Override
	public List<String> getPermissionList(Object id, String loginType) {
		log.info("getPermissionList loginType = {}", loginType);
		return new ArrayList<>();
	}

	@Override
	public List<String> getRoleList(Object id, String loginType) {
		log.info("getRoleList loginType = {}", loginType);
		return new ArrayList<>();
	}
}
