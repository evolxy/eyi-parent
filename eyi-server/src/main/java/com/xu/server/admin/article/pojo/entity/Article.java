package com.xu.server.admin.article.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/6 10:08
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "eyi_article")
@Data
public class Article extends BaseEntity {
	@TableField
	private String articleId;

	@TableField
	private String subTitle;

	@TableField
	private String title;

	@TableField
	private Integer viewCount;

	@TableField
	private Integer likeCount;

	@TableField
	private Integer commentCount;
}
