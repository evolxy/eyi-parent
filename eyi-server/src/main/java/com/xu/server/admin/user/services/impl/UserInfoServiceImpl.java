package com.xu.server.admin.user.services.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.xu.server.admin.user.entities.EyiUser;
import com.xu.server.admin.user.repository.UserInfoRepository;
import com.xu.server.admin.user.services.IUserInfoService;
import com.xu.server.admin.user.vo.LoginUserVo;
import com.xu.server.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 9:46
 */

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<EyiUser, UserInfoRepository> implements IUserInfoService {
    @Override
    public String login(LoginUserVo vo) {
        String username = vo.getUsername();
        String password = vo.getPassword();
        byte delFlag = 0;
        EyiUser user = repository.findByUsernameAndDelFlag(username, delFlag);
        if (password.equals(user.getPassword())) {
            StpUtil.login(username);
        }
        return StpUtil.getTokenValue();
    }
}
