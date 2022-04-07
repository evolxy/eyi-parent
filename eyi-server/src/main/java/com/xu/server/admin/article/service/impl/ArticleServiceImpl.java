package com.xu.server.admin.article.service.impl;

import com.xu.commons.exception.EyiException;
import com.xu.server.admin.article.pojo.document.ArticleDoc;
import com.xu.server.admin.article.pojo.entity.Article;
import com.xu.server.admin.article.pojo.entity.Catalog;
import com.xu.server.admin.article.pojo.vo.ArticleVo;
import com.xu.server.admin.article.repository.IArticleRepository;
import com.xu.server.admin.article.repository.ICatalogRepository;
import com.xu.server.admin.article.service.IArticleService;
import com.xu.server.base.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/1 14:40
 */
@Component
@AllArgsConstructor
public class ArticleServiceImpl extends BaseServiceImpl<Article, IArticleRepository> implements IArticleService {
    private final MongoTemplate template;
    private final IArticleRepository articleRepository;
    private final ICatalogRepository catalogRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleDoc save(ArticleVo articleVo) {
        ArticleDoc doc = new ArticleDoc();
        BeanUtils.copyProperties(articleVo, doc);
        Article article = new Article();
        BeanUtils.copyProperties(articleVo, article);
        // 保存基础信息到mysql中
        template.save(doc);
        article.setArticleId(doc.getId().toHexString());
        articleRepository.save(article);
        List<Catalog> catalogs = articleVo.getCatalogs();
        if (CollectionUtils.isNotEmpty(catalogs)) {
            catalogRepository.saveAll(catalogs);
        }
        return doc;
    }

    @Override
    public ArticleDoc update(ArticleVo articleVo) throws EyiException {
        ArticleDoc doc = new ArticleDoc();
        BeanUtils.copyProperties(articleVo, doc);
        Article article = new Article();
        BeanUtils.copyProperties(articleVo, article);
        Article art = articleRepository.findByArticleIdAndDelFlag(articleVo.getId().toHexString(), (byte) 0);
        if (art != null) {
            article.setId(art.getId());
        } else {
            throw new EyiException("逻辑错误");
        }
        List<Catalog> catalogs = articleVo.getCatalogs();
        if (CollectionUtils.isNotEmpty(catalogs)) {
            catalogRepository.saveAll(catalogs);
        }
        return doc;
    }

    @Override
    public boolean removeById(Long id) {
        return false;
    }
}
