package com.xu.server.admin.article.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.admin.article.pojo.entity.Catalog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/1 16:41
 */
@Repository
@Mapper
public interface ICatalogRepository extends BaseMapper<Catalog> {
    @Select("SELECT ec.* FROM eyi_catalog ec JOIN eyi_article_catalog ac ON ac.catalog_id = ec.id WHERE  ac.article_id = #{id}")
    List<Catalog> selectCatalogByArticleId(@Param("id") Long id);
}
