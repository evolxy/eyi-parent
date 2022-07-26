package com.xu.server.api.user.controller;

import com.xu.commons.result.Result;
import com.xu.server.api.user.pojo.vo.ApiUserInfoVo;
import com.xu.server.api.user.service.IApiUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/7/25 22:45
 */

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/user")
public class ApiUserController {
    private final IApiUserService userService;

    @GetMapping
    public Result<?> masterUserInfo() {
        ApiUserInfoVo infoVo = userService.queryMasterUserInfo();
        if (infoVo == null) {
            return Result.failed();
        } else {
            return Result.ok(infoVo);
        }
    }
}
