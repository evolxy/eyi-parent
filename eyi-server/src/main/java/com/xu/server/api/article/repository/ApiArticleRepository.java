package com.xu.server.api.article.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xu.server.api.article.pojo.vo.ApiArticleVo;
import com.xu.server.api.article.pojo.vo.ApiCatalogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/29 16:26
 */

@Mapper
@Repository
public interface ApiArticleRepository {
	/**
	 * 查询文章
	 * @param userId 创作者
	 * @param page 分页
	 * @return list
	 */
	Page<ApiArticleVo> selectApiArticleInfoList(@Param("page") Page<ApiArticleVo> page, @Param("userId") Long userId);

	/**
	 * 查询标签
	 * @param articleIds 文章id
	 * @return list
	 */
	List<ApiCatalogVo> selectCatalogByArticleId(@Param("articleIds") List<Long> articleIds);
}
