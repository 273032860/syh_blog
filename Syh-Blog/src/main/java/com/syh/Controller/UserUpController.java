package com.syh.Controller;


import com.syh.domain.ResponseResult;
import com.syh.domain.entity.SysUser;
import com.syh.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Api(tags="个人信息模块")
@RestController
@RequestMapping("/user")
public class UserUpController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value="个人信息更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name="avatar",value="头像地址",required=true,paramType="String"),
            @ApiImplicitParam(name="id",value="id",required=true,paramType="Integer"),
            @ApiImplicitParam(name="email",value="邮箱",required=true,paramType="String"),
            @ApiImplicitParam(name="nickname",value="昵称",required=true,paramType="String")
    })
//    @PreAuthorize("hasAuthority('oa:manage')")
    @PutMapping("/upuserinfo")
    public ResponseResult Userupinfo(@RequestBody SysUser sysUser1){
        //?pageNum=1&pageSize=1&categoryId=1 传参格式
//        System.out.println(""+avatar + email +id +nickName);
        ResponseResult result = sysUserService.Userupinfo(sysUser1);
        return result;
    }

    @ApiOperation(value="注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name="password",value="密码",required=true,paramType="String"),
            @ApiImplicitParam(name="id",value="id",required=true,paramType="Integer"),
            @ApiImplicitParam(name="email",value="邮箱",required=true,paramType="String"),
            @ApiImplicitParam(name="nickname",value="昵称",required=true,paramType="String")
    })
//    @PreAuthorize("hasAuthority('oa:manage')")
    @PostMapping("/userregister")
    public ResponseResult Userregister(@RequestBody SysUser sysuser2){

        ResponseResult result = sysUserService.Userregister(sysuser2);
        return result;
    }
}
