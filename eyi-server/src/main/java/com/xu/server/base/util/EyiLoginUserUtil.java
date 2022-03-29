package com.xu.server.base.util;

import cn.dev33.satoken.stp.StpUtil;
import com.xu.server.admin.user.vo.LoginUserVo;
import com.xu.server.base.dto.LoginUserDto;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/28 16:51
 */

public class EyiLoginUserUtil {
    public static LoginUserDto loginUser() {
        if (StpUtil.isLogin()) {
            long loginUserId = StpUtil.getLoginIdAsLong();
        }
        return null;
    }
}
