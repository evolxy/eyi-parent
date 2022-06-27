package com.xu.server.admin.article.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.admin.article.pojo.entity.Catalog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/1 16:41
 */
@Repository
@Mapper
public interface ICatalogRepository extends BaseMapper<Catalog> {
}
