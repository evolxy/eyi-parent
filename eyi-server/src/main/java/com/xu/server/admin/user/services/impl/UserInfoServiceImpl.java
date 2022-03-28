package com.xu.server.admin.user.services.impl;

import com.xu.server.admin.user.entities.EyiUser;
import com.xu.server.admin.user.repository.UserInfoRepository;
import com.xu.server.admin.user.services.IUserInfoService;
import com.xu.server.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 9:46
 */

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<EyiUser, UserInfoRepository> implements IUserInfoService {
}
