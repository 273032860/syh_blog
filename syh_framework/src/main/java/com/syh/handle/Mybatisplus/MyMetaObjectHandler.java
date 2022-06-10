package com.syh.handle.Mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始自动插入");
//        Long userId = null;
////        try {
//            userId = SecurityUtils.getUserId();
//        } catch (Exception e) {
//            e.printStackTrace();
//            userId = -1L;//表示是自己创建
//        }
        this.setFieldValByName("create_time", new Date(), metaObject);
        this.setFieldValByName("created", new Date(), metaObject);
        this.setFieldValByName("update_time", new Date(), metaObject);
//        this.setFieldValByName("createBy",userId , metaObject);
        this.setFieldValByName("updated", new Date(), metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);

//        this.setFieldValByName("updateBy", userId, metaObject);
        log.info("插入的数据"+metaObject.toString());
        log.info("当前时间"+ new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始自动更新");
        this.setFieldValByName("updated", new Date(), metaObject);
        this.setFieldValByName("update_time", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
//        this.setFieldValByName(" ", SecurityUtils.getUserId(), metaObject);
        log.info("更新的数据"+metaObject);
    }
}