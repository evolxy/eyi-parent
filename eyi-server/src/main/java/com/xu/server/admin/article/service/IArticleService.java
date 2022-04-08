package com.xu.server.admin.article.service;

import com.xu.commons.exception.EyiException;
import com.xu.server.admin.article.pojo.document.ArticleDoc;
import com.xu.server.admin.article.pojo.entity.Article;
import com.xu.server.admin.article.pojo.vo.ArticleVo;
import com.xu.server.base.service.IBaseService;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/1 13:49
 */

public interface IArticleService extends IBaseService<Article> {
    ArticleDoc save(ArticleVo article);

    ArticleDoc update(ArticleVo articleVo) throws EyiException;

    boolean removeById(Long id);

    ArticleDoc getById(String id);
}
