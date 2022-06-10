package com.syh.Controller;


import com.syh.domain.ResponseResult;
import com.syh.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags="友链模块")
@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @ApiOperation(value="友情链接",notes="查询全部友情链接")

//    @PreAuthorize("hasAuthority('oa:manage')")
    @GetMapping("/linkall")
    public ResponseResult ArticleList() {//?pageNum=1&pageSize=1&categoryId=1 传参格式
        ResponseResult result = linkService.LinkAll();
        return result;
    }


}
