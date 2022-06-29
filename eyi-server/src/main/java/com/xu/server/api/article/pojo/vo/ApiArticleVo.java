package com.xu.server.api.article.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/29 16:19
 */
@Data
public class ApiArticleVo {
	@JsonIgnore
	private Long id;
	private String articleId;

	private String title;

	private String subTitle;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime updateTime;

	private int commentCount;

	private int likeCount;

	private String createName;

	private String createAvatar;

	List<ApiCatalogVo> catalogs;
}
