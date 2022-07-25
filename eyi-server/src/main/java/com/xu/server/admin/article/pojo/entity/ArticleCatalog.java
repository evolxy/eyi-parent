package com.xu.server.admin.article.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/7/25 14:01
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "eyi_article")
@Data
public class ArticleCatalog extends BaseEntity {
	private Long catalogId;

	private Long articleId;
}
