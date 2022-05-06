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
    /**
     * 保存
     * @param article article
     * @return article
     */
    ArticleDoc save(ArticleVo article);

    /**
     * 更新
     * @param articleVo article
     * @return article
     * @throws EyiException Exception
     */
    ArticleDoc update(ArticleVo articleVo) throws EyiException;

    /**
     * 删除
     * @param id id
     * @return true | false
     */
    @Override
    boolean removeById(Long id);

    /**
     * 根据id获取文章详情
     * @param id id
     * @return art
     */
    ArticleDoc getById(String id);
}
