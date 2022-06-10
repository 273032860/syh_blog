package com.syh.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syh.domain.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【syh_category】的数据库操作Mapper
* @createDate 2022-05-19 15:32:51
* @Entity com.syh.domain.entity.Category
*/
public interface CategoryMapper extends BaseMapper<Category> {
    List<String> selectcategoryId();
}




