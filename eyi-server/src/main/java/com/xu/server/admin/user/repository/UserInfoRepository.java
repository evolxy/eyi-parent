package com.xu.server.admin.user.repository;

import com.xu.server.admin.user.entities.EyiUser;
import com.xu.server.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/15 16:55
 */
@Repository
public interface UserInfoRepository extends BaseRepository<EyiUser> {
    EyiUser findByUsernameAndDelFlag(String username, byte delFlag);
}
