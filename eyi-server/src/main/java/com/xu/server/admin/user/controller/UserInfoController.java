package com.xu.server.admin.user.controller;

import com.xu.commons.result.Result;
import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.pojo.vo.LoginUserVo;
import com.xu.server.admin.user.services.IUserInfoService;
import com.xu.server.base.controller.BaseController;
import com.xu.server.base.pojo.bo.LoginUserBo;
import com.xu.server.base.util.EyiLoginUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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
        String token = service.login(vo);
        return StringUtils.isNotBlank(token) ?Result.ok("登录成功").data(token):Result.failed("登录失败");
    }

    @PostMapping("/logout")
    @ApiOperation("登出")
    public Result<?> logout() {
        LoginUserBo loginUser = EyiLoginUserUtil.loginUser();
        if (null!=loginUser) {
            boolean logout = service.logout(loginUser);
        }
        return null!=loginUser?Result.ok("登出成功"):Result.failed("未登录");
    }

    @GetMapping("/userInfo")
    @ApiOperation("userInfo")
    public Result<?> userInfo() {
        LoginUserBo loginUserBo = EyiLoginUserUtil.loginUser();
        if (loginUserBo==null) {
            return Result.failed("请登录");
        } else {
            return getById(loginUserBo.getId());
        }
    }
}
