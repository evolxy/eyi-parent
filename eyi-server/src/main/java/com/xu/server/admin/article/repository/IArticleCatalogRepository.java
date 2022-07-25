package com.xu.server.admin.article.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.admin.article.pojo.entity.ArticleCatalog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/7/25 14:06
 */

@Mapper
@Repository
public interface IArticleCatalogRepository extends BaseMapper<ArticleCatalog> {
}
