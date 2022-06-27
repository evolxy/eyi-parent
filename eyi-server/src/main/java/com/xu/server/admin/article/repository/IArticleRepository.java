package com.xu.server.admin.article.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xu.server.admin.article.pojo.entity.Article;
import com.xu.server.admin.article.pojo.vo.ArticleRespVo;
import com.xu.server.admin.article.pojo.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/6 10:28
 */
@Repository
@Mapper
public interface IArticleRepository extends BaseMapper<Article> {
    /**
     * 根据id查询文章
     * @param toHexString mongodb object id
     * @param delFlag 删除标记
     * @return article or null
     */
    ArticleRespVo findByArticleIdAndDelFlag(@Param("articleId") String toHexString, @Param("delFlag") int delFlag);


    /**
     * 分页查询
     * @param page page
     * @param qw qw
     * @return page
     */
    Page<ArticleVo> selectArticleVoPage(Page<ArticleVo> page, @Param(Constants.WRAPPER) QueryWrapper<Article> qw);
}
