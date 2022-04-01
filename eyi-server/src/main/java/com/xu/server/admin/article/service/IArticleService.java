package com.xu.server.admin.article.service;

import com.xu.server.admin.article.pojo.document.Article;
import org.bson.types.ObjectId;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/1 13:49
 */

public interface IArticleService {
    Article save(Article article);

    Article update(Article article);

    boolean removeById(ObjectId id);
}
