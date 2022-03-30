package com.xu.server.admin.user.services;

import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.pojo.vo.LoginUserVo;
import com.xu.server.base.pojo.bo.LoginUserBo;
import com.xu.server.base.service.IBaseService;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 9:46
 */
public interface IUserInfoService extends IBaseService<EyiUser> {
    String login(LoginUserVo vo);

    boolean logout(LoginUserBo loginUser);
}
