package com.xu.server.admin.user.controller;

import com.xu.commons.exception.EyiException;
import com.xu.commons.result.Result;
import com.xu.server.admin.user.entities.EyiUser;
import com.xu.server.admin.user.services.IUserInfoService;
import com.xu.server.base.controller.BaseController;
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
@RequestMapping("/eyi/user")
@Api(value = "server-admin-用户管理", tags = "server-admin-用户管理")
public class UserInfoController extends BaseController<EyiUser, IUserInfoService> {

}
