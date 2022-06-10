package com.syh.Controller;



import com.syh.domain.ResponseResult;
import com.syh.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="分类模块")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("分类列表")
//    @PreAuthorize("hasAuthority('oa:manage')")
    @GetMapping("/categoryList")
    public ResponseResult hotArticleList() {
        ResponseResult result = categoryService.categoryList();
        return result;
    }
}
