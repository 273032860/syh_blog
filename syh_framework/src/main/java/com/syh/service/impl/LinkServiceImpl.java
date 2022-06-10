package com.syh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syh.constants.SystemConstants;
import com.syh.domain.ResponseResult;
import com.syh.domain.entity.Link;
import com.syh.domain.vo.HotArticleVo;
import com.syh.domain.vo.LinkVo;
import com.syh.mapper.LinkMapper;
import com.syh.service.LinkService;

import com.syh.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【syh_link】的数据库操作Service实现
* @createDate 2022-05-20 16:59:11
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
    implements LinkService{

    @Override
    public ResponseResult LinkAll() {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(wrapper);

        //vo
        List<LinkVo> vs = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return ResponseResult.okResult(vs);
    }
}




