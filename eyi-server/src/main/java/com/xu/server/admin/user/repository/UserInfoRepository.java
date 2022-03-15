package com.xu.server.admin.user.repository;

import com.xu.server.admin.user.entities.EyiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/15 16:55
 */
@Repository
public interface UserInfoRepository extends JpaRepository<EyiUser, Long> {
}
