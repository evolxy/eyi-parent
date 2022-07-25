package com.xu.server.admin.user.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.xu.commons.result.Result;
import com.xu.commons.result.ResultCode;
import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.pojo.entities.EyiUserAdditionalInfo;
import com.xu.server.admin.user.pojo.vo.CaptchaReqVo;
import com.xu.server.admin.user.pojo.vo.ChangePassVo;
import com.xu.server.admin.user.pojo.vo.LoginUserVo;
import com.xu.server.admin.user.pojo.vo.UserInfoVo;
import com.xu.server.admin.user.services.IUserInfoService;
import com.xu.server.base.controller.BaseController;
import com.xu.server.base.pojo.bo.LoginUserBo;
import com.xu.server.base.util.LoginUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/14 14:51
 */
@RestController
@Slf4j
@RequestMapping("/admin/user")
@Api(value = "server-admin-用户管理", tags = "server-admin-用户管理")
public class UserInfoController extends BaseController<EyiUser, IUserInfoService> {


	@PostMapping("/login")
	@ApiOperation("登录")
	public Result<?> login(@RequestBody LoginUserVo vo) {
		boolean check = service.checkCaptcha(vo);
		if (!check) {
			return Result.failed("验证码错误");
		}
		String token = service.login(vo);
		return StringUtils.isNotBlank(token) ? Result.ok("登录成功").data(token) : Result.failed("登录失败");
	}

	@PostMapping("/changePasswd")
	@ApiOperation("修改密码")
	@SaCheckLogin
	public Result<?> changePasswd(@RequestBody ChangePassVo vo) {

		boolean changed = service.changePassWord(vo);
		return changed ? Result.ok("修改成功") : Result.failed("原密码错误");
	}

	@PostMapping("/logout")
	@ApiOperation("登出")
	public Result<?> logout() {
		LoginUserBo loginUser = LoginUserUtil.loginUser();
		if (null != loginUser) {
			boolean logout = service.logout(loginUser);
		}
		return null != loginUser ? Result.ok("登出成功") : Result.failed("未登录");
	}

	@GetMapping("/userInfo")
	@ApiOperation("userInfo")
	@SaCheckLogin
	public Result<?> userInfo() {
		LoginUserBo loginUserBo = LoginUserUtil.loginUser();
		if (loginUserBo == null) {
			return Result.failed(ResultCode.NOT_LOGIN);
		} else {
			return Result.ok(service.getById(loginUserBo.getId()));
		}
	}

	@GetMapping("/addition")
	@SaCheckLogin
	public Result<?> userAdditionalInfo() {
		LoginUserBo loginUserBo = LoginUserUtil.loginUser();
		if (loginUserBo == null) {
			return Result.failed(ResultCode.NOT_LOGIN);
		} else {
			return Result.ok(service.getAdditionalInfo(loginUserBo.getId()));
		}
	}

	@PostMapping("/change")
	@SaCheckLogin
	public Result<?> userAdditionalInfoChange(@RequestBody EyiUserAdditionalInfo info) {
		Long loginUserId = LoginUserUtil.loginUserId();
		if (loginUserId != null) {
			info.setId(loginUserId);
		} else {
			return Result.failed(ResultCode.NOT_LOGIN);
		}
		boolean updated = service.updateAdditionalInfo(info);
		return updated ? Result.ok() : Result.failed();
	}

	@PostMapping("")
	@ApiOperation("update user info")
	@SaCheckLogin
	public Result<?> updateUserInfo(@RequestBody UserInfoVo userInfo) {
		boolean changed = service.updateUserBaseInfo(userInfo);
		return changed ? Result.ok() : Result.failed();
	}

	@GetMapping("/captcha")
	@ApiOperation("captcha")
	public Result<?> captcha(CaptchaReqVo vo) {
		Map<String, String> code = service.getCode(vo);
		return Result.ok(code);
	}


	@GetMapping("/spare")
	@ApiOperation("激活")
	public String activeEmail(Long key) {
		boolean res = service.activeEmail(key, 2);
		StringBuilder base = new StringBuilder();
		String address = "localhost";
		try {
			address = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		address = address + ":12002" ;
		if (res) {
			base.append("激活成功, 请登录[http://").append(address).append("/user/login]");
		} else {
			base.append("激活失败！");
		}
		return base.toString();
	}
}
