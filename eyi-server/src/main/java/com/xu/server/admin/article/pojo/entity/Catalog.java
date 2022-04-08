package com.xu.server.admin.article.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 文章标签
 * @author Author
 * @version 0.1
 * Created On 2022/4/1 16:10
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "eyi_catalog")
@Data
public class Catalog extends BaseEntity {
    private static final long serialVersionUID = 3737899427754241L;

    private String catalogName;

    private String catalogCode;

    @ManyToMany(mappedBy = "catalogs")
    @JsonIgnore
    private List<Article> articles;
}
