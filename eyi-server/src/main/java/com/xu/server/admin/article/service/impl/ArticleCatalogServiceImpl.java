package com.xu.server.admin.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.server.admin.article.pojo.entity.ArticleCatalog;
import com.xu.server.admin.article.repository.IArticleCatalogRepository;
import com.xu.server.admin.article.service.IArticleCatalogService;
import org.springframework.stereotype.Service;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/7/25 14:08
 */
@Service
public class ArticleCatalogServiceImpl extends ServiceImpl<IArticleCatalogRepository, ArticleCatalog>  implements IArticleCatalogService {
}
