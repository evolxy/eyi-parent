package com.xu.server.admin.article.service.impl;

import com.xu.server.admin.article.pojo.document.Article;
import com.xu.server.admin.article.service.IArticleService;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Component;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/1 14:40
 */
@Component
public class ArticleServiceImpl implements IArticleService {
    private final MongoTemplate template;

    public ArticleServiceImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public Article save(Article article) {
        return template.save(article);
    }

    @Override
    public Article update(Article article) {
        return null;
    }

    @Override
    public boolean removeById(ObjectId id) {
        return false;
    }
}
