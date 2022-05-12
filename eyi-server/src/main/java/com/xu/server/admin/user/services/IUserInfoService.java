package com.xu.server.admin.user.services;

import cn.dev33.satoken.stp.StpInterface;
import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.pojo.vo.LoginUserVo;
import com.xu.server.base.pojo.bo.LoginUserBo;
import com.xu.server.base.service.IBaseService;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 9:46
 */
public interface IUserInfoService extends IBaseService<EyiUser>, StpInterface {
    /**
     * 登录
     * @param vo 登录参数
     * @return token
     */
    String login(LoginUserVo vo);

    /**
     * 登出
     * @param loginUser 已登录用户
     * @return ture|false
     */
    boolean logout(LoginUserBo loginUser);
}
