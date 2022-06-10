package com.syh.Controller;


import com.syh.domain.ResponseResult;
import com.syh.domain.entity.Article;
import com.syh.service.ArticleService;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="文章模块")
@RestController
@RequestMapping("/article")
public class ArticleController {




    @Autowired
    private ArticleService articleService;


    @ApiOperation("热度排名")
    @ApiImplicitParams({
            @ApiImplicitParam()
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
//    @PreAuthorize("hasAuthority('oa:manage')")
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        ResponseResult result = articleService.hotArticleList();
        return result;
    }


    @ApiOperation(value="文章列表",notes="第n页，每页n条，分类id(选填)")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum",value="第n页",required=true,paramType="Integer"),
            @ApiImplicitParam(name="pageSize",value="每页n条",required=true,paramType="Integer"),
            @ApiImplicitParam(name="categoryId",value="分类id",paramType="Integer")
    })
//    @PreAuthorize("hasAuthority('oa:manage')")
    @GetMapping("/articleList")
    public ResponseResult ArticleList(Integer pageNum, Integer pageSize, Long categoryId) {//?pageNum=1&pageSize=1&categoryId=1 传参格式
        ResponseResult result = articleService.ArticleList(pageNum,pageSize,categoryId);
        return result;
    }


    @ApiOperation("文章详情")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "文章id")
    })
//    @PreAuthorize("hasAuthority('oa:manage')")
    @GetMapping("/{id}")
    public ResponseResult ArticleDetail(@PathVariable("id")Long id) {//路径传参模式
        ResponseResult result = articleService.ArticleDetail(id);
        return result;
    }


    @ApiOperation("发布文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name="title",value="标题",required=true,paramType="String"),
            @ApiImplicitParam(name="content",value="文章内容",required=true,paramType="String"),
            @ApiImplicitParam(name="category_id",value="所属分类",required=true,paramType="Integer"),
            @ApiImplicitParam(name="thumbnail",value="图片",paramType="String"),
            @ApiImplicitParam(name="is_top",value="是否置顶",required=true,paramType="Char"),
            @ApiImplicitParam(name="status",value="是否发布0已发布1保存",required=true,paramType="Char"),
            @ApiImplicitParam(name="is_comment",value="是否允许评论",required=true,paramType="Char"),
            @ApiImplicitParam(name="create_by",value="发布id",required=true,paramType="Integer"),
    })
//    @PreAuthorize("hasAuthority('oa:manage')")
    @PostMapping("/savearticle")
    public ResponseResult Savearticle(@RequestBody Article article) {//路径传参模式
        ResponseResult result = articleService.savearticle(article);
        return result;
    }

}
