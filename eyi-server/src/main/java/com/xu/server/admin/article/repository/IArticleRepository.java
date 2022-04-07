package com.xu.server.admin.article.repository;

import com.xu.server.admin.article.pojo.entity.Article;
import com.xu.server.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/6 10:28
 */
@Repository
public interface IArticleRepository extends BaseRepository<Article> {
    Article findByArticleIdAndDelFlag(String toHexString, byte delFlag);
}
