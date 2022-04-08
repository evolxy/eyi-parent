package com.xu.server.admin.article.pojo.entity;

import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/6 10:08
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "eyi_article")
@Data
public class Article extends BaseEntity {
    private String articleId;

    private String subTitle;

    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "eyi_article_catalog",schema = "eyi",
            joinColumns = {@JoinColumn(name = "articleId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "catalogId", referencedColumnName = "id")}
    )
    private List<Catalog> catalogs;
}
