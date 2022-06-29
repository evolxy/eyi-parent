package com.xu.server.api.article.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/29 16:36
 */

@Data
public class ApiCatalogVo {
	@JsonIgnore
	private Long articleId;

	private String catalogName;

	private String catalogCode;
}
