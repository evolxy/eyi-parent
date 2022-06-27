package com.xu.server.admin.article.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.admin.article.pojo.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/6 10:28
 */
@Repository
@Mapper
public interface IArticleRepository extends BaseMapper<Article> {
    /**
     * 根据id查询文章
     * @param toHexString mongodb object id
     * @param delFlag 删除标记
     * @return article or null
     */
    Article findByArticleIdAndDelFlag(String toHexString, int delFlag);
}
