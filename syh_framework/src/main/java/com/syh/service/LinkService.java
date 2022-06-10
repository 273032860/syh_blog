package com.syh.service;

import com.syh.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import com.syh.domain.ResponseResult;

/**
* @author Lenovo
* @description 针对表【syh_link】的数据库操作Service
* @createDate 2022-05-20 16:59:11
*/
public interface LinkService extends IService<Link> {

    ResponseResult LinkAll();
}
