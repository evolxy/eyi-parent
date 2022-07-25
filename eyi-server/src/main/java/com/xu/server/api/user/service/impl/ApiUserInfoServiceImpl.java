package com.xu.server.api.user.service.impl;

import com.xu.server.api.user.pojo.vo.ApiUserInfoVo;
import com.xu.server.api.user.repository.IApiUserRepository;
import com.xu.server.api.user.service.IApiUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/7/25 22:57
 */

@Service
@AllArgsConstructor
public class ApiUserInfoServiceImpl implements IApiUserService {
    private final IApiUserRepository userRepository;

    @Override
    public ApiUserInfoVo queryMasterUserInfo() {
        return userRepository.selectStationMasterInfo();
    }
}
