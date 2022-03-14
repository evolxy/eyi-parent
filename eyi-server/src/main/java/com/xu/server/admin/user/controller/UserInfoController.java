package com.xu.server.admin.user.controller;

import com.xu.commons.exception.EyiException;
import com.xu.commons.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/14 14:51
 */
@RestController
@Slf4j
@RequestMapping("/eyi/user")
public class UserInfoController {
    @GetMapping("/exp")
    public Result<?> testExp(@RequestParam String param) throws Exception {
        if (StringUtils.equals(param, "11")) {
            throw new EyiException("eyi param = "+param);
        } else if (StringUtils.equals(param, "22")) {
            throw new Exception("exp param = "+param);
        }
        return Result.ok();
    }
}
