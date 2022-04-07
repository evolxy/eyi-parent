package com.xu.server.admin.article.pojo.entity;

import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/6 10:58
 */
@EqualsAndHashCode(callSuper = true)
@Table(name = "eyi_article_catalog",schema = "eyi")
@Entity
@Data
public class ArticleCatalog extends BaseEntity {
    private Long articleId;

    private Long catalogId;
}
