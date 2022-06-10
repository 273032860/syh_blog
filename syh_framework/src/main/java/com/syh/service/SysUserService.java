package com.syh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.syh.domain.ResponseResult;
import com.syh.domain.entity.SysUser;

/**
* @author Lenovo
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2022-05-17 16:32:34
*/
public interface SysUserService extends IService<SysUser> {

    ResponseResult login(SysUser sysUser);

    ResponseResult loginout();


    ResponseResult Userupinfo(SysUser sysUser1);

    ResponseResult Userregister(SysUser sysuser2);
}
