package com.xu.server.api.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xu.commons.exception.EyiLogicException;
import com.xu.server.api.article.pojo.vo.ApiArticleDocVo;
import com.xu.server.api.article.pojo.vo.ApiArticleVo;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/29 16:45
 */

public interface IApiArticleService {
	/**
	 * 分页查询
	 * @param userId userId
	 * @param current current
	 * @param size  size
	 * @return page
	 */
	Page<ApiArticleVo> queryArticlePageByUserId(int current, int size, Long userId);

	/**
	 * 获取文章详情
	 * @param articleId aid
	 * @return vo
	 * @throws EyiLogicException e
	 */
	ApiArticleDocVo queryArticleDetailByArticleId(String articleId) throws EyiLogicException;
}
