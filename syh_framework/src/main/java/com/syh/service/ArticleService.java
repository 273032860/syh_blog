package com.syh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.syh.domain.ResponseResult;
import com.syh.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();


    ResponseResult ArticleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult ArticleDetail(Long id);

    ResponseResult savearticle(Article article);

}
