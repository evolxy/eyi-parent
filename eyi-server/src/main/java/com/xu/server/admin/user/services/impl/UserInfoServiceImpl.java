package com.xu.server.admin.user.services.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.repository.UserInfoRepository;
import com.xu.server.admin.user.services.IUserInfoService;
import com.xu.server.admin.user.pojo.vo.LoginUserVo;
import com.xu.server.base.pojo.bo.LoginUserBo;
import com.xu.server.base.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 9:46
 */

@Service
@Slf4j
@CacheConfig(cacheNames = "user")
public class UserInfoServiceImpl extends BaseServiceImpl<EyiUser, UserInfoRepository> implements IUserInfoService {
    @Override
    @Cacheable(key = "#vo.username")
    public String login(LoginUserVo vo) {
        String username = vo.getUsername();
        String password = vo.getPassword();
        byte delFlag = 0;
        EyiUser user = repository.findByUsernameAndDelFlag(username, delFlag);
        if (password.equals(user.getPassword())) {
            StpUtil.login(user.getId());
        }
        return StpUtil.getTokenValue();
    }

    @Override
    @CacheEvict(key = "#loginUser.username")
    public boolean logout(LoginUserBo loginUser) {
        if (loginUser!=null) {
            StpUtil.logout();
        }
        return true;
    }
}
