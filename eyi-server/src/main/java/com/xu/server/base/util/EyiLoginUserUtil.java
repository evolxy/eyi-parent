package com.xu.server.base.util;

import cn.dev33.satoken.stp.StpUtil;
import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.repository.UserInfoRepository;
import com.xu.server.base.pojo.bo.LoginUserBo;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/28 16:51
 */

public class EyiLoginUserUtil {
    public static LoginUserBo loginUser() {
        if (StpUtil.isLogin()) {
            long loginUserId = StpUtil.getLoginIdAsLong();
            UserInfoRepository repository = ApplicationContextUtil.getBean(UserInfoRepository.class);
            Optional<EyiUser> optional = repository.findById(loginUserId);
            if (optional.isPresent()) {
                EyiUser user = optional.get();
                LoginUserBo bo = new LoginUserBo();
                BeanUtils.copyProperties(user, bo);
                return bo;
            }
        }
        return null;
    }
}
