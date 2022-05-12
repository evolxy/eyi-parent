package com.xu.server.admin.user.repository;

import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/15 16:55
 */
@Repository
public interface UserInfoRepository extends BaseRepository<EyiUser> {
    /**
     * 查询用户
     * @param username 用户名
     * @param delFlag 删除标记
     * @return user
     */
    EyiUser findByUsernameAndDelFlag(String username, byte delFlag);
}
