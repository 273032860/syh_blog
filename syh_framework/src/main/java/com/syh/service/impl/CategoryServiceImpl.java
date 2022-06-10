package com.syh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syh.constants.SystemConstants;
import com.syh.domain.ResponseResult;
import com.syh.domain.entity.Article;
import com.syh.domain.entity.Category;
import com.syh.domain.vo.CategoryVo;
import com.syh.domain.vo.HotArticleVo;
import com.syh.service.ArticleService;
import com.syh.service.CategoryService;
import com.syh.mapper.CategoryMapper;
import com.syh.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【syh_category】的数据库操作Service实现
* @createDate 2022-05-19 15:32:51
*/
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

//    @Autowired
//    private ArticleService articleService;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResponseResult categoryList() {
        List result = categoryMapper.selectList(null);
//        System.out.println("结果:"+result);
        List<CategoryVo> vs = BeanCopyUtils.copyBeanList(result, CategoryVo.class);
//        log.info(result);
        return ResponseResult.okResult(vs);
    }
}




