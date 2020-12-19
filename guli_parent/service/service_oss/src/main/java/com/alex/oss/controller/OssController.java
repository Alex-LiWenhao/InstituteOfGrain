package com.alex.oss.controller;

import com.alex.commonutils.R;
import com.alex.oss.Service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssController
 * @Description TODO :
 * @Author Alex
 * @Date 2020/11/24 10:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;
    /**
    *功能描述 获取前端传送过来的文件
    * @author Alex
    * @Date 2020/12/18 23:01
    * @param file
    * @return com.alex.commonutils.R
    */
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }
}
