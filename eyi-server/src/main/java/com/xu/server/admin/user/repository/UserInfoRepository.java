package com.xu.server.admin.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.admin.user.pojo.entities.EyiUser;
import com.xu.server.admin.user.pojo.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/15 16:55
 */
@Repository
@Mapper
public interface UserInfoRepository extends BaseMapper<EyiUser> {
    /**
     * 查询用户
     * @param username 用户名
     * @param delFlag 删除标记
     * @return user
     */
    @Select("SELECT * FROM eyi_user WHERE del_flag = #{delFlag} and username=#{username}")
    EyiUser findByUsernameAndDelFlag(@Param("username") String username, @Param("delFlag") int delFlag);

    /**
     * 根据id 获取 角色编码列表
     * @param uId 用户id
     * @return list
     */
    @Select( value = "SELECT r.role_code FROM eyi_user u " +
            "LEFT JOIN eyi_user_role ur ON u.id = ur.user_id " +
            "LEFT JOIN eyi_role r ON ur.role_id = r.id " +
            "WHERE  u.id = ?1 AND u.del_flag=0 AND r.del_flag=0")
    List<String> findRoleCodeListById(long uId);

    /**
     * 根据id 获取 权限编码列表
     * @param uId 用户id
     * @return list
     */
    @Select(value = "SELECT p.permission_code FROM eyi_user u " +
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

    /**
     * 修改用户基础信息
     * @param userInfo 基础信息
     * @return true|false
     */

    @Select("UPDATE eyi_user SET nickname=#{user.nickname}, " +
            "birthday=#{user.birthday},introduce=#{user.introduce}, " +
            "gender=#{user.gender}, avatar=#{user.avatar} where id=#{user.id}")
	int updateBaseInfo(@Param("user") UserInfoVo userInfo);
}
