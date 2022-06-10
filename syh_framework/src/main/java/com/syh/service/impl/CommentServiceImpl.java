package com.syh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syh.constants.SystemConstants;
import com.syh.domain.ResponseResult;
import com.syh.domain.entity.Comment;
import com.syh.domain.vo.ArticleListVo;
import com.syh.domain.vo.CommentVo;
import com.syh.domain.vo.HotArticleVo;
import com.syh.domain.vo.PageVo;
import com.syh.enums.AppHttpCodeEnum;
import com.syh.exception.SystemException;
import com.syh.mapper.SysUserMapper;
import com.syh.service.CommentService;
import com.syh.mapper.CommentMapper;
import com.syh.service.SysUserService;
import com.syh.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
* @author Lenovo
* @description 针对表【syh_comment】的数据库操作Service实现
* @createDate 2022-05-21 13:52:48
*/
@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public ResponseResult CommentList(Integer pageNum, Integer pageSize, Long article_id) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Comment> rootwrapper = new LambdaQueryWrapper<>();
        if (article_id != null) {
            wrapper.eq(Comment::getArticleId, article_id);
        }
        wrapper.eq(Comment::getRootId, -1);
        //显示10条,使用分页查询10条1页
        Page<Comment> page = new Page(pageNum, pageSize);
        page(page, wrapper);
        log.info("获取的wrapper"+ wrapper.toString());
        log.info("获取的page"+page.getRecords());
//LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
//wrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
//List<Link> links = list(wrapper);//查询完毕包装接收，准备vo
//List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
//return ResponseResult.okResult(vs);
        //分页vo
        List<CommentVo> vs = BeanCopyUtils.copyBeanList(page.getRecords(), CommentVo.class);

        for(CommentVo vs1 : vs){
            vs1.setUsername(sysUserMapper.selectById(vs1.getCreateBy()).getNickname());
            if(vs1.getToComUserId()!=-1){
                vs1.setToComUserName(sysUserMapper.selectById(vs1.getToComUserId()).getNickname());
            }

            List<Comment> Commentroot=list(rootwrapper.eq(Comment::getRootId,vs1.getId()));//用La查询rootid
            List<CommentVo> vs2 = BeanCopyUtils.copyBeanList(Commentroot, CommentVo.class);//包装到Vo
            vs1.setChildren(vs2);//使用set方法把数据放入Vo类 List<CommentVo> children;
        }
        return ResponseResult.okResult(new PageVo(vs,page.getTotal()));
    }


    @Override
    public ResponseResult addComment(Comment comment) {


        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }

        //从token中获取用户信息:工具类SecurityUtils,+mybatisplus自动填充MyMetaObjectHandler封装类,需在是体力中加注解
        //@TableField(fill = FieldFill.INSERT)   @TableField(fill = FieldFill.INSERT_UPDATE)
        save(comment);
        return ResponseResult.okResult();
    }
}






