package com.xu.server.admin.article.pojo.vo;

import com.xu.server.admin.article.pojo.entity.Article;
import com.xu.server.admin.article.pojo.entity.Catalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/27 20:46
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleRespVo extends Article {
    private List<Catalog> catalogs;
}
