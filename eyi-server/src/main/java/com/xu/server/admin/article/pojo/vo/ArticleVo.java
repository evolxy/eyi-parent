package com.xu.server.admin.article.pojo.vo;

import com.xu.server.admin.article.pojo.document.ArticleDoc;
import com.xu.server.admin.article.pojo.entity.Catalog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/6 10:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleVo extends ArticleDoc {
    private List<Catalog> catalogs;
}
