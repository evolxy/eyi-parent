package com.xu.server.api.user.repository;

import com.xu.server.api.user.pojo.vo.ApiUserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/7/25 22:46
 */

@Mapper
@Repository
public interface IApiUserRepository {
    /**
     * 获取博主账号 即id = 1的账号
     * @return
     */
    @Select("select email, gender, introduce, birthday, nickname, avatar\n" +
            "from eyi_user\n" +
            "WHERE id = 1")
    ApiUserInfoVo selectStationMasterInfo();
}
