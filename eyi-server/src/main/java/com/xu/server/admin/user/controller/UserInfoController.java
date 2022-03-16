package com.xu.server.admin.user.controller;

import com.xu.commons.exception.EyiException;
import com.xu.commons.result.Result;
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
public class UserInfoController {
    @GetMapping("/{id}")
    @ApiOperation("server-admin-用户管理-测试")
    public Result<?> getUserById(@PathVariable String id) {
        return Result.ok();
    }
}
