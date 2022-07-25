package com.xu.server.admin.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mongodb.client.result.UpdateResult;
import com.xu.commons.exception.EyiException;
import com.xu.server.admin.article.pojo.document.ArticleDoc;
import com.xu.server.admin.article.pojo.entity.Article;
import com.xu.server.admin.article.pojo.entity.ArticleCatalog;
import com.xu.server.admin.article.pojo.entity.Catalog;
import com.xu.server.admin.article.pojo.vo.ArticleReqParam;
import com.xu.server.admin.article.pojo.vo.ArticleVo;
import com.xu.server.admin.article.repository.IArticleRepository;
import com.xu.server.admin.article.service.IArticleCatalogService;
import com.xu.server.admin.article.service.IArticleService;
import com.xu.server.admin.article.service.ICatalogService;
import com.xu.server.base.service.impl.BaseServiceImpl;
import com.xu.server.base.util.QueryWrapperCreator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/1 14:40
 */
@Component
@AllArgsConstructor
@Slf4j
public class ArticleServiceImpl extends BaseServiceImpl<Article, IArticleRepository> implements IArticleService {
	private final MongoTemplate template;
	private final IArticleRepository articleRepository;
	private final ICatalogService catalogService;

	private final IArticleCatalogService articleCatalogService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ArticleDoc save(ArticleReqParam reqParam) {
		ArticleDoc doc = new ArticleDoc();
		BeanUtils.copyProperties(reqParam, doc);
		Article article = new Article();
		BeanUtils.copyProperties(reqParam, article);
		// 保存基础信息到mysql中
		template.save(doc);
		article.setArticleId(doc.getId().toHexString());
		save(article);
		// 插入关联表
		List<Catalog> catalogs = reqParam.getCatalogs();
		List<ArticleCatalog> articleCatalogs = new ArrayList<>();
		Long articleId = article.getId();
		if (CollectionUtils.isNotEmpty(catalogs)) {
			catalogs = catalogService.getOrSave(catalogs);
			catalogs.forEach(it -> {
				ArticleCatalog articleCatalog = new ArticleCatalog();
				articleCatalog.setCatalogId(it.getId());
				articleCatalog.setArticleId(articleId);
				articleCatalogs.add(articleCatalog);
			});
		}
		if (CollectionUtils.isNotEmpty(articleCatalogs)) {
			//  save
			articleCatalogService.saveBatch(articleCatalogs);
		}
		return doc;
	}

	@Override
	public ArticleDoc update(ArticleReqParam reqParam) throws EyiException {
		// 保存完整信息到mongodb中
		ArticleDoc doc = new ArticleDoc();
		BeanUtils.copyProperties(reqParam, doc);
		doc.setId(reqParam.getArticleId());

		Query query = new Query(Criteria.where("id").is(reqParam.getArticleId()));
		Update update = new Update();
		update.set("content", reqParam.getContent());
		update.set("mdContent", reqParam.getMdContent());
		update.set("title", reqParam.getTitle());
		update.set("subTitle", reqParam.getSubTitle());
		update.set("updateTime", LocalDateTime.now());
		UpdateResult result = template.updateFirst(query, update, ArticleDoc.class);
		if (result.getModifiedCount() <= 0) {
			throw new EyiException("更新失败");
		}
		// 保存数基础信息据库
		ArticleVo article = new ArticleVo();
		BeanUtils.copyProperties(reqParam, article);
		article.setArticleId(reqParam.getArticleId().toHexString());
		List<Catalog> catalogs = reqParam.getCatalogs();
		if (CollectionUtils.isNotEmpty(catalogs)) {
			catalogs = catalogService.getOrSave(catalogs);
			article.setCatalogs(catalogs);
			saveOrUpdate(article);
		}
		return doc;
	}

	@Override
	public boolean removeById(Long id) {
		// 删除数据库中的
		Article article = articleRepository.selectById(id);
		if (article == null) {
			return true;
		}
		int logicDel = articleRepository.deleteById(id);
		log.info("从数据库中删除 id=[{}] 的数据", id);
		String articleId = article.getArticleId();
		if (StringUtils.isNotBlank(articleId)) {
			Query query = new Query(Criteria.where("id").is(new ObjectId(articleId)));
			Update update = new Update();
			update.set("delFlag", true);
			UpdateResult result = template.updateFirst(query, update, ArticleDoc.class);
			if (result.getModifiedCount() > 0 && logicDel > 0) {
				log.info("删除成功");
			}
		}
		return logicDel > 0;
	}

	@Override
	public ArticleDoc getById(String id) {
		if (ObjectId.isValid(id)) {
			ObjectId objectId = new ObjectId(id);
			Query query = new Query(Criteria.where("id").is(objectId).and("delFlag").is(false));
			return template.findOne(query, ArticleDoc.class);
		} else {
			log.error("ObjectId 不合法 {}", id);
			return null;
		}
	}

	@Override
	public Page<ArticleVo> queryArticleVoPage(Integer pageNo, Integer pageSize, Article article) {
		Page<ArticleVo> pp = new Page<>(pageNo, pageSize);
		QueryWrapper<Article> qw = QueryWrapperCreator.create(article);
		Page<ArticleVo> res = baseMapper.selectArticleVoPage(pp, qw);
		return res;
	}
}
