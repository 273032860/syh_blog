package com.syh.Controller;


import com.syh.domain.ResponseResult;
import com.syh.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags="头像上传模块")
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;


    @ApiOperation(value="头像上传",notes="返回oss地址")
    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){//img 就是传参的key
        System.out.println("上传过来的请求:"+img);
        return  uploadService.uploadImg(img);
    }

}
