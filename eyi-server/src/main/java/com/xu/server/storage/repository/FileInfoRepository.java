package com.xu.server.storage.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.storage.pojo.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/18 15:45
 */
@Repository
@Mapper
public interface FileInfoRepository extends BaseMapper<FileInfo> {
}
