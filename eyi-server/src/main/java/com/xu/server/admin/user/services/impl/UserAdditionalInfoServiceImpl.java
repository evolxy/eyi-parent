package com.xu.server.admin.user.services.impl;

import com.xu.server.admin.user.pojo.entities.EyiUserAdditionalInfo;
import com.xu.server.admin.user.repository.UserAdditionalInfoRepository;
import com.xu.server.admin.user.services.IUserAdditionalInfoService;
import com.xu.server.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/28 10:56
 */
@Service
public class UserAdditionalInfoServiceImpl extends BaseServiceImpl<EyiUserAdditionalInfo, UserAdditionalInfoRepository> implements IUserAdditionalInfoService {
}
