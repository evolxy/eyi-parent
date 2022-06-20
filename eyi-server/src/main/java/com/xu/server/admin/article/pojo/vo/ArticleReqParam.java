package com.xu.server.admin.article.pojo.vo;

import com.xu.server.admin.article.pojo.entity.Catalog;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/20 16:29
 */
@Data
public class ArticleReqParam {
	private ObjectId articleId;

	private String subTitle;

	private String title;

	private Long  id;

	private List<Catalog> catalogs;

	private String content;

	private String mdContent;
}
