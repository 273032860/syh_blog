package com.syh.service;

import com.syh.domain.ResponseResult;
import com.syh.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Lenovo
* @description 针对表【syh_comment】的数据库操作Service
* @createDate 2022-05-21 13:52:48
*/
public interface CommentService extends IService<Comment> {

    ResponseResult CommentList(Integer pageNum, Integer pageSize, Long article_id);

    ResponseResult addComment(Comment comment);
}
