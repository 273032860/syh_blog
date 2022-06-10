package com.syh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.syh.domain.LoginUser;
import com.syh.mapper.SysMenuMapper;
import com.syh.mapper.SysUserMapper;
import com.syh.domain.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();// 构建一个查询的wrapper
        wrapper.eq(SysUser::getUsername,username);
        SysUser result = sysUserMapper.selectOne(wrapper);

        if(Objects.isNull(result)) {
            throw new RuntimeException("用户名密码错误");
        }
        log.info("查询SQL通过");
//        List<String> list = new ArrayList<>(Arrays.asList("test"));
        List<String> list = sysMenuMapper.selectPermsByUserId(result.getId());
        return new LoginUser(result,list);
    }
}
