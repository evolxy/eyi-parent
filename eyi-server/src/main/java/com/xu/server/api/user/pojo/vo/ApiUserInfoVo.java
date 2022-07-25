package com.xu.server.api.user.pojo.vo;

import com.xu.server.base.enums.GenderEnum;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/7/25 22:47
 */

@Data
public class ApiUserInfoVo {
    private String avatar;

    private String email;

    private GenderEnum gender;

    private String introduce;

    private LocalDate birthday;

    private String nickname;
}
