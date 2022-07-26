package com.xu.server.api.article.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/7/26 10:49
 */

@Data
public class ApiArticleDocVo {
	private String title;
	/**
	 * 副标题/简介 长度限制在100字
	 */
	private String subTitle;

	/**
	 * 编译后的h5
	 */
	private String content;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime updateTime;
}
