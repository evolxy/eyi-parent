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
import com.xu.server.base.util.EyiLoginUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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
	public Result<?> changePasswd(@RequestBody ChangePassVo vo) {

		boolean changed = service.changePassWord(vo);
		return changed ? Result.ok("修改成功") : Result.failed("原密码错误");
	}

	@PostMapping("/logout")
	@ApiOperation("登出")
	public Result<?> logout() {
		LoginUserBo loginUser = EyiLoginUserUtil.loginUser();
		if (null != loginUser) {
			boolean logout = service.logout(loginUser);
		}
		return null != loginUser ? Result.ok("登出成功") : Result.failed("未登录");
	}

	@GetMapping("/userInfo")
	@ApiOperation("userInfo")
	public Result<?> userInfo() {
		LoginUserBo loginUserBo = EyiLoginUserUtil.loginUser();
		if (loginUserBo == null) {
			return Result.failed(ResultCode.NOT_LOGIN);
		} else {
			return Result.ok(service.getById(loginUserBo.getId()));
		}
	}

	@GetMapping("/addition")
	public Result<?> userAdditionalInfo() {
		LoginUserBo loginUserBo = EyiLoginUserUtil.loginUser();
		if (loginUserBo == null) {
			return Result.failed(ResultCode.NOT_LOGIN);
		} else {
			return Result.ok(service.getAdditionalInfo(loginUserBo.getId()));
		}
	}

	@PostMapping("/change")
	public Result<?> userAdditionalInfoChange(@RequestBody EyiUserAdditionalInfo info) {
		Long loginUserId = EyiLoginUserUtil.loginUserId();
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
}
