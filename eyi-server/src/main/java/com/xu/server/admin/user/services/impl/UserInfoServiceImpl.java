package com.xu.server.admin.user.services.impl;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.xu.commons.exception.EyiException;
import com.xu.server.admin.user.constant.CaptchaConstant;
import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.pojo.vo.ChangePassVo;
import com.xu.server.admin.user.pojo.vo.LoginUserVo;
import com.xu.server.admin.user.repository.UserInfoRepository;
import com.xu.server.admin.user.services.IUserInfoService;
import com.xu.server.admin.user.util.Captcha;
import com.xu.server.base.pojo.bo.LoginUserBo;
import com.xu.server.base.service.impl.BaseServiceImpl;
import com.xu.server.base.util.RedisUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 9:46
 */

@Service
@Slf4j
@CacheConfig(cacheNames = "user")
public class UserInfoServiceImpl extends BaseServiceImpl<EyiUser, UserInfoRepository> implements IUserInfoService {
	@Override
	@Cacheable(key = "#vo.username")
	public String login(LoginUserVo vo) {
		String username = vo.getUsername();
		String password = vo.getPassword();
		byte delFlag = 0;
		EyiUser user = repository.findByUsernameAndDelFlag(username, delFlag);
		if (user == null) {
			throw new SaTokenException("用户不存在");
		}
		if (BCrypt.checkpw(password, user.getPassword())) {
			StpUtil.login(user.getId());
		}
		return StpUtil.getTokenValue();
	}

	@Override
	@CacheEvict(key = "#loginUser.username")
	public boolean logout(LoginUserBo loginUser) {
		if (loginUser != null) {
			StpUtil.logout();
		}
		return true;
	}

	@Override
	public String getCode(OutputStream os) {
		String captcha = Captcha.createImgCaptcha(os);
		String captchaId = UUID.randomUUID().toString().replaceAll("-", "");
		RedisUtils.set(CaptchaConstant.CAPTCHA_PREFIX + captchaId, captcha, CaptchaConstant.EXPIRE_SECONDS);
		return captchaId;
	}

	@Override
	public boolean checkCaptcha(LoginUserVo vo) {
		String captcha = vo.getCode();
		String id = vo.getCodeId();
		Object o = RedisUtils.get(CaptchaConstant.CAPTCHA_PREFIX + id);
		if (o == null) {
			return false;
		}
		return StringUtils.equals(captcha, o.toString());
	}

	@Override
	public boolean changePassWord(ChangePassVo vo) {
		String oldPass = vo.getOldPassword();

		String loginIdStr = StpUtil.getLoginIdByToken(StpUtil.getTokenValue()).toString();

		long loginUserId = Long.parseLong(loginIdStr);

		byte delFlag = 1;
		EyiUser user = getById(loginUserId);
		if (user == null || user.getDelFlag() == delFlag) {
			throw new SaTokenException("用户不存在");
		}
		if (BCrypt.checkpw(oldPass, user.getPassword())) {
			String hashNewPass = BCrypt.hashpw(vo.getNewPassword(), BCrypt.gensalt());
			user.setPassword(hashNewPass);
			saveOrUpdate(user);
			return true;
		} else {
			return false;
		}
	}

	@SneakyThrows
	@Override
	public List<String> getPermissionList(Object id, String loginType) {
		long uId;
		try {
			uId = Long.parseLong(id.toString());
		} catch (Exception e) {
			throw new EyiException("id 无效 " + id);
		}
		List<String> roleCodes = repository.findRoleCodeListById(uId);
		return CollectionUtils.isEmpty(roleCodes) ? new ArrayList<>() : roleCodes;
	}

	@SneakyThrows
	@Override
	public List<String> getRoleList(Object id, String loginType) {
		long uId;
		try {
			uId = Long.parseLong(id.toString());
		} catch (Exception e) {
			throw new EyiException("id 无效 " + id);
		}
		List<String> permissionCode = repository.findPermissionCodeListById(uId);
		return CollectionUtils.isEmpty(permissionCode) ? new ArrayList<>() : permissionCode;
	}
}
