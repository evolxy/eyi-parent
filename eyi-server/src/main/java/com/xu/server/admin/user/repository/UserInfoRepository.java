package com.xu.server.admin.user.repository;

import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.base.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    EyiUser findByUsernameAndDelFlag(String username, int delFlag);

    /**
     * 根据id 获取 角色编码列表
     * @param uId 用户id
     * @return list
     */
    @Query(nativeQuery = true, value = "SELECT r.role_code FROM eyi_user u " +
            "LEFT JOIN eyi_user_role ur ON u.id = ur.user_id " +
            "LEFT JOIN eyi_role r ON ur.role_id = r.id " +
            "WHERE  u.id = ?1 AND u.del_flag=0 AND r.del_flag=0")
    List<String> findRoleCodeListById(long uId);

    /**
     * 根据id 获取 权限编码列表
     * @param uId 用户id
     * @return list
     */
    @Query(nativeQuery = true, value = "SELECT p.permission_code FROM eyi_user u " +
            "LEFT JOIN eyi_user_role ur ON u.id = ur.user_id " +
            "LEFT JOIN eyi_role_permission rp ON ur.role_id = rp.role_id " +
            "LEFT JOIN eyi_permission p ON p.id = rp.permission_id " +
            "WHERE  u.id = ?1 AND u.del_flag=0 AND p.del_flag=0")
    List<String> findPermissionCodeListById(long uId);

    /**
     * 查询用户
     * @param email 用户名
     * @param delFlag 删除标记
     * @return user
     */
    EyiUser findByEmailAndDelFlag(String email, int delFlag);
}
