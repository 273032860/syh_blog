package com.syh;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
//@MapperScan("com.syh.domain.mapper")//扫描mapper接口
@MapperScan("com.syh.mapper")
public class SyhBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SyhBlogApplication.class, args);
    }
}
