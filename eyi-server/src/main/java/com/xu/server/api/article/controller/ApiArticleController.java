package com.xu.server.api.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xu.commons.result.Result;
import com.xu.server.api.article.pojo.vo.ApiArticleVo;
import com.xu.server.api.article.service.IApiArticleService;
import com.xu.server.base.pojo.bo.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/29 17:03
 */
@RestController
@Api(tags = "eyi-api-article", value = "eyi-api-article")
@RequestMapping("/api/article")
@AllArgsConstructor
public class ApiArticleController {
	private final IApiArticleService apiArticleService;

	@GetMapping("/list")
	@ApiOperation("文章列表")
	public Result<?> pageList(@RequestParam(defaultValue = "1") int current,  @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) Long authorId) {
		Page<ApiArticleVo> page = apiArticleService.queryArticlePageByUserId(current, size, authorId);
		PageParam<ApiArticleVo> res = PageParam.convertToPage(page);
		return Result.ok(res);
	}
}
