package com.xu.server.admin.user.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/28 10:35
 */
@Data
public class LoginUserVo {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
