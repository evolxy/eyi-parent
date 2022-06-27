package com.xu.server.admin.user.services.impl;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.xu.commons.exception.EyiException;
import com.xu.server.admin.user.constant.CaptchaConstant;
import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.pojo.vo.CaptchaReqVo;
import com.xu.server.admin.user.pojo.vo.ChangePassVo;
import com.xu.server.admin.user.pojo.vo.LoginUserVo;
import com.xu.server.admin.user.pojo.vo.UserInfoVo;
import com.xu.server.admin.user.repository.UserInfoRepository;
import com.xu.server.admin.user.services.IUserInfoService;
import com.xu.server.admin.user.util.Captcha;
import com.xu.server.base.enums.DelFlagEnum;
import com.xu.server.base.pojo.bo.LoginUserBo;
import com.xu.server.base.service.impl.BaseServiceImpl;
import com.xu.server.base.util.BeanPropsUtils;
import com.xu.server.base.util.EyiLoginUserUtil;
import com.xu.server.base.util.RedisUtils;
import com.xu.server.email.pojo.EmailInfo;
import com.xu.server.email.service.EmailService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 9:46
 */

@Service
@Slf4j
public class UserInfoServiceImpl extends BaseServiceImpl< EyiUser, UserInfoRepository> implements IUserInfoService {

	private final EmailService emailService;

	public UserInfoServiceImpl(EmailService emailService) {
		this.emailService = emailService;
	}

	@Override
//	@Cacheable(key = "#vo.username")
	public String login(LoginUserVo vo) {
		String username = vo.getUsername();
		String password = vo.getPassword();
		String email = vo.getEmail();
		EyiUser user = null;
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			// 比较
			user = baseMapper.findByUsernameAndDelFlag(username, DelFlagEnum.NOT_DELETED.getValue());
		}
		if (StringUtils.isNotBlank(email)) {
			user = baseMapper.findByEmailAndDelFlag(email, DelFlagEnum.NOT_DELETED.getValue());
		}

		if (user == null) {
			throw new SaTokenException("用户不存在");
		}
		if (BCrypt.checkpw(password, user.getPassword())) {
			StpUtil.login(user.getId());
		}
		return StpUtil.getTokenValue();
	}

	@Override
//	@CacheEvict(key = "#loginUser.username")
	public boolean logout(LoginUserBo loginUser) {
		if (loginUser != null) {
			StpUtil.logout();
		}
		return true;
	}

	@Override
	public Map<String, String> getCode(CaptchaReqVo vo) {
		String code = Captcha.generateCaptcha();
		Map<String, String> codeMap = new HashMap<>(16);
		String captchaId = UUID.randomUUID().toString().replaceAll("-", "");
		RedisUtils.set(CaptchaConstant.CAPTCHA_PREFIX + captchaId, code, CaptchaConstant.EXPIRE_SECONDS);
		codeMap.put("id", captchaId);
		switch (vo.getType()) {
			case EMAIL:
				// 发送邮件
				HashMap<String, Object> params = new HashMap<>(4);
				params.put("captcha", code);
				params.put("expire", CaptchaConstant.EXPIRE_SECONDS);
				emailService.sendMsg(params, new EmailInfo(vo.getEmail(), "登录验证码"), "emails/captcha.ftlh");
				break;
			case WEB:
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Captcha.createImgCaptcha(bos, code);
				Base64.Encoder encoder = Base64.getEncoder();
				String imgCaptcha = encoder.encodeToString(bos.toByteArray());
				codeMap.put("img", imgCaptcha);
				break;
			default:
				break;
		}
		return codeMap;
	}

	@Override
	public boolean checkCaptcha(LoginUserVo vo) {
		String captcha = vo.getCaptcha();
		String id = vo.getCodeId();
		String key = CaptchaConstant.CAPTCHA_PREFIX + id;
		Object o = RedisUtils.get(key);
		RedisUtils.delete(key);
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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateUserBaseInfo(UserInfoVo userInfo) {
		Long id = EyiLoginUserUtil.loginUserId();
		if (id == null) {
			return false;
		}
		EyiUser user = new EyiUser();
		BeanPropsUtils.copyNotNullProps(userInfo, user);
		user.setId(id);
		return saveOrUpdate(user);
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
		List<String> roleCodes = baseMapper.findRoleCodeListById(uId);
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
		List<String> permissionCode = baseMapper.findPermissionCodeListById(uId);
		return CollectionUtils.isEmpty(permissionCode) ? new ArrayList<>() : permissionCode;
	}
}
