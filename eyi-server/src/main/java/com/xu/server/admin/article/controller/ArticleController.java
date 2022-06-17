package com.xu.server.admin.article.controller;

import com.xu.commons.result.Result;
import com.xu.server.admin.article.pojo.document.ArticleDoc;
import com.xu.server.admin.article.pojo.entity.Article;
import com.xu.server.admin.article.pojo.vo.ArticleVo;
import com.xu.server.admin.article.service.IArticleService;
import com.xu.server.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/7 15:29
 */
@RestController
@RequestMapping("/admin/article")
@Api(tags = "eyi-admin-文章管理")
@AllArgsConstructor
public class ArticleController extends BaseController<Article, IArticleService> {
    @PostMapping("")
    @ApiOperation("保存")
    public Result<?> save(@RequestBody ArticleVo articleVo) {
        ArticleDoc save = service.save(articleVo);
        return save.getId()!=null?Result.ok("保存成功"):Result.failed("保存失败");
    }
}
