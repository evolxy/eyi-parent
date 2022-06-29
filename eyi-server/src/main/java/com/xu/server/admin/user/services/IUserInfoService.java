package com.xu.server.admin.user.services;

import cn.dev33.satoken.stp.StpInterface;
import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.pojo.entities.EyiUserAdditionalInfo;
import com.xu.server.admin.user.pojo.vo.CaptchaReqVo;
import com.xu.server.admin.user.pojo.vo.ChangePassVo;
import com.xu.server.admin.user.pojo.vo.LoginUserVo;
import com.xu.server.admin.user.pojo.vo.UserInfoVo;
import com.xu.server.base.pojo.bo.LoginUserBo;
import com.xu.server.base.service.IBaseService;

import java.util.Map;

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

    /**
     * 获取图片验证码 并将图片验证码存入redis中备用
     * @param vo 验证码类型
     * @return string captcha
     */
    Map<String, String> getCode(CaptchaReqVo vo);

    /**
     * 校验验证码
     * @param vo vo
     * @return ture | false
     */
    boolean checkCaptcha(LoginUserVo vo);

    /**
     * 修改密码
     * @param vo vo
     * @return changed
     */
    boolean changePassWord(ChangePassVo vo);

    /**
     * 修改基础信息
     * @param userInfo user info
     * @return true| false
     */
	boolean updateUserBaseInfo(UserInfoVo userInfo);

    /**
     * 附加信息表
     * @param id id
     * @return user info
     */
    EyiUserAdditionalInfo getAdditionalInfo(Long id);

    /**
     * 更新附加信息
     * @param info info
     * @return true
     */
    boolean updateAdditionalInfo(EyiUserAdditionalInfo info);

    /**
     * 激活邮箱
     * @param key key
     * @param type 类型 1 邮箱 2 备用邮箱
     * @return boolean
     */
    boolean activeEmail(Long key, int type);
}
