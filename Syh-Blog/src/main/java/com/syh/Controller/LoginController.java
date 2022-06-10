package com.syh.Controller;


import com.syh.domain.ResponseResult;
import com.syh.domain.entity.SysUser;
import com.syh.exception.SystemException;
import com.syh.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.syh.enums.AppHttpCodeEnum;

@Api(tags="登录登出模块")
@RestController
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("登录列表")
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody SysUser sysUser) {
        //登录,验证如果用户名没传抛出自定义异常，自定义打印信息和用法查看GlobalExceptionHandler
        if(!StringUtils.hasText(sysUser.getUsername())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }

        return sysUserService.login(sysUser);
    }
    @ApiOperation("登出列表")
    @GetMapping("/user/loginout")
    public ResponseResult loginout(){
        //退出登录
        return sysUserService.loginout();
    }


}