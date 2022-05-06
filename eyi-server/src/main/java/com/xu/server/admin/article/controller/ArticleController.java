package com.xu.server.admin.article.controller;

import com.xu.commons.exception.EyiException;
import com.xu.commons.result.Result;
import com.xu.server.admin.article.pojo.document.ArticleDoc;
import com.xu.server.admin.article.pojo.entity.Article;
import com.xu.server.admin.article.pojo.vo.ArticleVo;
import com.xu.server.admin.article.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/7 15:29
 */
@RestController
@RequestMapping("/eyi/admin/article")
@Api(tags = "eyi-admin-文章管理")
@AllArgsConstructor
public class ArticleController {
    private final IArticleService articleService;

    @GetMapping("{id}")
    @ApiOperation("查询详情")
    public Result<?> getById(@PathVariable String id) {
        ArticleDoc articleDoc = articleService.getById(id);
        return articleDoc != null ? Result.ok(articleDoc) : Result.failed().msg("查询失败");
    }

    @GetMapping("/page")
    @ApiOperation("查询分页")
    public Result<?> getPageList(
            @RequestBody(required = false) Article article,
            @RequestParam(defaultValue = "1", name = "page") Integer pageNo,
            @RequestParam(defaultValue = "10", name = "size") Integer pageSize) {
        Page<Article> articles = articleService.page(pageNo, pageSize, article);
        return Result.ok(articles);
    }

    @PostMapping("")
    @ApiOperation("保存")
    public Result<?> save(@RequestBody ArticleVo articleVo) {
        ArticleDoc save = articleService.save(articleVo);
        return save.getId()!=null?Result.ok("保存成功"):Result.failed("保存失败");
    }

    @PutMapping("")
    @ApiOperation("修改")
    public Result<?> modify(@RequestBody ArticleVo articleVo) {
        ArticleDoc upd;
        try {
            upd = articleService.update(articleVo);
            return upd.getId()!=null?Result.ok("保存成功"):Result.failed("保存失败");
        } catch (EyiException e) {
            return Result.failed(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除")
    public Result<?> deleteById(@PathVariable Long id) {
        boolean removed = articleService.removeById(id);
        return removed ? Result.ok("删除成功") : Result.failed().msg("删除失败");
    }
}
