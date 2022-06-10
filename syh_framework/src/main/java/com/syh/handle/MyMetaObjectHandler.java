//package com.syh.handle;
//
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.reflection.MetaObject;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//
//import java.util.Date;
//
///**
// * 自动生成时间
// */
//@Slf4j
//@Component
//public class MyMetaObjectHandler implements MetaObjectHandler {
//
//    /**
//     * 插入时的填充策略
//     *
//     * @param metaObject
//     */
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        log.info("start insert fill ...");
//        this.setFieldValByName("createTime", new Date(), metaObject);
//        this.setFieldValByName("updateTime", new Date(), metaObject);
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        log.info("start update fill ...");
//        this.setFieldValByName("updateTime", new Date(), metaObject);
//    }
//}