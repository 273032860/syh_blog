package com.syh.service;

import com.syh.domain.ResponseResult;

import com.baomidou.mybatisplus.extension.service.IService;
import com.syh.domain.entity.Category;

/**
* @author Lenovo
* @description 针对表【syh_category】的数据库操作Service
* @createDate 2022-05-19 15:32:51
*/
public interface CategoryService extends IService<Category> {

    ResponseResult categoryList();
}
