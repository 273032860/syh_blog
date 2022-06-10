package com.syh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syh.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2022-05-17 16:32:34
* @Entity com.syh.domain.entity.Article.SysMenu
*/
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<String> selectPermsByUserId(@Param("userid") Long userid);
}




