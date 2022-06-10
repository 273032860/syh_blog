package com.syh.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.syh.domain.LoginUser;
import com.syh.domain.ResponseResult;
import com.syh.domain.vo.SysUserVo;
import com.syh.enums.AppHttpCodeEnum;
import com.syh.exception.SystemException;
import com.syh.mapper.SysUserMapper;
import com.syh.domain.entity.SysUser;
import com.syh.service.SysUserService;
import com.syh.utils.BeanCopyUtils;
import com.syh.utils.JwtUtil;
import com.syh.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
* @author Lenovo
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2022-05-17 16:32:34
*/
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private PasswordEncoder passwordEncoder;//注入加密

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(SysUser sysUser) {
        //AuthenticationManager authenticationManager进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword());
        log.info("开始认证" + authenticationToken);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        log.info("定位问题");
        //如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        //如果认证通过，使用userid生成一个jwt jwt存入ReaponseResult返回
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        log.info("认证通过authenticate.getPrincipal()获得了" + principal);
        String userid = principal.getSysUser().getId().toString();
        log.info("获取到ID" + userid);
        String jwt = JwtUtil.createJWT(userid);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("token", jwt);
        //把完整的用户信息存入redis   userid作为key
        //查询userinfo put进map中
//        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(SysUser::getId, userid);
//        List<SysUser> SysUser = list(wrapper);//查询完毕包装接收，准备vo

//        List<SysUserVo> vs = BeanCopyUtils.copyBeanList(SysUser, SysUserVo.class);
        log.info("生成SysUser" + principal.getSysUser());
        SysUserVo sysUserVo = BeanCopyUtils.copyBean(principal.getSysUser(), SysUserVo.class);
        objectObjectHashMap.put("userInfo", sysUserVo);
        redisCache.setCacheObject("login:" + userid, principal);
        return new ResponseResult(200, "登录成功", objectObjectHashMap);
    }


    @Override
    public ResponseResult loginout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getSysUser().getId();
        //删除redis的值
        redisCache.deleteObject("login:" + userid);
        return new ResponseResult(200, "注销成功");
    }

    @Override
    public ResponseResult Userupinfo(SysUser sysUser1) {


        SysUser sysUser2 = Optional.ofNullable(sysUserMapper.selectById(sysUser1.getId())).orElseThrow(RuntimeException::new);
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<>();
        if(StringUtils.hasText(sysUser1.getAvatar())){
            wrapper.set(SysUser::getAvatar, sysUser1.getAvatar());
        }
        if(StringUtils.hasText(sysUser1.getEmail())){
        wrapper.set(SysUser::getEmail, sysUser1.getEmail());
        }
        if(StringUtils.hasText(sysUser1.getNickname())){
        wrapper.set(SysUser::getNickname, sysUser1.getNickname());
        }
        if(StringUtils.hasText(sysUser1.getPhone())){
        wrapper.set(SysUser::getPhone, sysUser1.getPhone());
        }
        wrapper.eq(SysUser::getId, sysUser1.getId());
        int i = sysUserMapper.update(sysUser2, wrapper);
        return ResponseResult.okResult(i);

    }

    @Override
    public ResponseResult Userregister(SysUser sysuser2) {
        //判断是否为空
        if(!StringUtils.hasText(sysuser2.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWOR_NOT_NULL);
        }
//        if(!StringUtils.hasText(user.getUsername())){
//            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
//        }
        Optional.ofNullable(sysuser2).map(SysUser::getUsername).orElseThrow(()->new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL));

        if(!StringUtils.hasText(sysuser2.getNickname())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //判断数据是否存在

//        LambdaUpdateWrapper<SysUser> Updatewrapper = new LambdaUpdateWrapper<>();//更新器
//        SysUser sysUser = new SysUser();
        LambdaQueryWrapper<SysUser> nickwrapper = new LambdaQueryWrapper<>();
        nickwrapper.eq(SysUser::getNickname, sysuser2.getNickname());
        Integer integer = sysUserMapper.selectCount(nickwrapper);
//        List<SysUser> list = list(nickwrapper);
//        System.out.println(integer);

        if(integer> 0){
            throw  new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        LambdaQueryWrapper<SysUser> userwrapper = new LambdaQueryWrapper<>();
        userwrapper.eq(SysUser::getUsername, sysuser2.getUsername());
        Integer integer2 = sysUserMapper.selectCount(userwrapper);
//        if(count(userwrapper)>0){
        if(integer2>0){
            throw  new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        //加密
        String password = passwordEncoder.encode((sysuser2.getPassword()));
        sysuser2.setPassword(password);
        sysUserMapper.insert(sysuser2);


        return ResponseResult.okResult(200,"注册成功!");
    }

    @Override
    public boolean saveBatch(Collection<SysUser> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<SysUser> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<SysUser> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(SysUser entity) {
        return false;
    }

    @Override
    public SysUser getOne(Wrapper<SysUser> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<SysUser> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<SysUser> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<SysUser> getBaseMapper() {
        return null;
    }

    @Override
    public Class<SysUser> getEntityClass() {
        return null;
    }
}
