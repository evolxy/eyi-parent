package com.xu.server.admin.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xu.commons.exception.EyiException;
import com.xu.server.admin.article.pojo.document.ArticleDoc;
import com.xu.server.admin.article.pojo.entity.Article;
import com.xu.server.admin.article.pojo.vo.ArticleReqParam;
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
     * @param reqParam article
     * @return article
     */
    ArticleDoc save(ArticleReqParam reqParam);

    /**
     * 更新
     * @param reqParam article
     * @return article
     * @throws EyiException Exception
     */
    ArticleDoc update(ArticleReqParam reqParam) throws EyiException;

    /**
     * 删除
     * @param id id
     * @return true | false
     */
    boolean removeById(Long id);

    /**
     * 根据id获取文章详情
     * @param id id
     * @return art
     */
    ArticleDoc getById(String id);

    /**
     * 分页查询
     * @param pageNo 当前页
     * @param pageSize size
     * @param article 查询条件
     * @return page article
     */
    Page<ArticleVo> queryArticleVoPage(Integer pageNo, Integer pageSize, Article article);
}
