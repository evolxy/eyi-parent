package com.xu.server.admin.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.admin.user.pojo.entities.EyiUserAdditionalInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/28 10:53
 */
@Mapper
@Repository
public interface UserAdditionalInfoRepository extends BaseMapper<EyiUserAdditionalInfo> {
}
