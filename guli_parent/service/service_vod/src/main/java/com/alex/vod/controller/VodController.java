package com.alex.vod.controller;

import com.alex.commonutils.R;
import com.alex.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName VodController
 * @Description TODO : 操作video的接口
 * @Author Alex
 * @Date 2020/12/26 20:59
 * @Version 1.0
 */
@Api(description = "对视频的一些操作")
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    @ApiModelProperty(value = "服务接口")
    private VodService vodService;

    /**
    *功能描述 上传视频到阿里云
    * @author Alex
    * @Date 2020/12/26 21:19
    * @param file
    * @return com.alex.commonutils.R
    */
    @PostMapping("uploadVideo")
    public R uploadVideo(MultipartFile file){
        String videoId = vodService.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }


}
