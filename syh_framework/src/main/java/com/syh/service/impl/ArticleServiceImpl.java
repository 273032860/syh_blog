package com.syh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syh.constants.SystemConstants;
import com.syh.domain.ResponseResult;
import com.syh.domain.entity.Article;
import com.syh.domain.entity.Category;
import com.syh.domain.entity.SysUser;
import com.syh.domain.vo.*;
import com.syh.enums.AppHttpCodeEnum;
import com.syh.exception.SystemException;
import com.syh.mapper.ArticleMapper;
import com.syh.service.ArticleService;
import com.syh.service.CategoryService;
import com.syh.service.SysUserService;
import com.syh.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();
//        bean拷贝
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        for (Article article : articles) {
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            articleVos.add(vo);
//        }
        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(vs);
    }

    @Override
    public ResponseResult ArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        //查询条件 1、如果有categoryId判断是否正确 2、按isTop进行排序 3、stauts 0
        //传参判断是否有传，用eq(判断语句 ,查询类字段, 传参变量) 是否有传使用 Objects.nonNull(传参变量)  &&且大于0
        wrapper.eq(Objects.nonNull(categoryId) && categoryId>0,Article::getCategoryId,categoryId);

        wrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        wrapper.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> page = new Page(pageNum,pageSize);
        page(page,wrapper);
        List <Article> articles2 = page.getRecords();
        List <Article> articles = page.getRecords();
        articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());



//        log.info(sysUserService.getById(sysUserService.getById(article.getCreateBy()).getAvatar()));
//        System.out.println(page.getRecords());

//        articles2.stream()
//                .map(new Function<Article, Article>() {
//                    @Override
//                    public Article apply(Article article) {
//                        return article.setAvatar(sysUserService.getById(article.getCreateBy()).getAvatar());
//                    }
//                })
////                .forEach(System.out.println(articles2));
//                .collect(Collectors.toList());

//        System.out.println(page.getRecords());
        //分页vo
        List<ArticleListVo> vs = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(vs,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult ArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
        //根据分类id查询




    }

    @Override
    public ResponseResult savearticle(Article article) {


        articleMapper.insert(article);
        return ResponseResult.okResult();
    }


}
