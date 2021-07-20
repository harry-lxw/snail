package com.winter.mapper;

import com.winter.bean.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章相关接口
 */
public interface ArticleMapper {
    //获取文章列表
    List<Article> getArticleList(Article article);

    //获取特别推荐文章 || 获取首页接口
    List<Article> getArticleListByStatus(@Param(value="recommendStatus")String recommendStatus,
                                 @Param(value="homePageStatus")String homePageStatus,
                                 @Param(value="labelID")String labelID,
                                 @Param(value="page")int page,
                                 @Param(value="limitNum")int limitNum);

    //获取文章详情
    List<Article> getArticleDetailByID(@Param(value="articleID")String articleID);

    //删除文章
    Integer deleteArticle(@Param(value="articleID")String articleID);

    //新增文章
    Integer addArticle(Article article);

    //修改文章
    Integer updateArticle(Article article);

    //修改文章点赞量+1
    Integer updateArticleSupportCount(@Param(value="articleID")String articleID);
    //修改文章浏览量+1
    Integer updateArticlePageViewCount(@Param(value="articleID")String articleID);

    Integer getArticleListCount(@Param(value="labelID")String labelID);

}
