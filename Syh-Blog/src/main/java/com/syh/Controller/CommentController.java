package com.syh.Controller;


import com.syh.domain.ResponseResult;
import com.syh.domain.entity.Comment;
import com.syh.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags="评论模块")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value="评论列表",notes="第n页，每页n条，分类id(选填)")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum",value="第n页",required=true,paramType="Integer"),
            @ApiImplicitParam(name="pageSize",value="每页n条",required=true,paramType="Integer"),
            @ApiImplicitParam(name="article_id",value="文章id",paramType="Integer")
    })
//    @PreAuthorize("hasAuthority('oa:manage')")
    @GetMapping("/commentList")
    public ResponseResult ArticleList(Integer pageNum, Integer pageSize, Long article_id) {//?pageNum=1&pageSize=1&categoryId=1 传参格式
        ResponseResult result = commentService.CommentList(pageNum,pageSize,article_id);
        return result;
    }


    @ApiOperation(value="增加评论",notes="第n页，每页n条，分类id(选填)")
    @ApiImplicitParams({
            @ApiImplicitParam(name="type",value="第n页",required=true,paramType="Integer"),
            @ApiImplicitParam(name="pageSize",value="每页n条",required=true,paramType="Integer"),
            @ApiImplicitParam(name="article_id",value="文章id",paramType="Integer")
    })
//    @PreAuthorize("hasAuthority('oa:manage')")
    @PostMapping("/addcomment")
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);

        }
}
