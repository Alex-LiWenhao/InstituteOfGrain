package com.alex.vod.controller;

import com.alex.commonutils.R;
import com.alex.servicebase.AlexException;
import com.alex.vod.service.VodService;
import com.alex.vod.utils.ConstantVideoPropertiesUtil;
import com.alex.vod.utils.InitObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
    *功能描述 根据视频ID去删除视频
    * @author Alex
    * @Date 2021/1/15 21:19
    * @param [id]
    * @return com.alex.commonutils.R
    */
    @DeleteMapping("removeAlyVideo/{id}")
    public R deleteAlyVideo(@PathVariable String id){
        try {
            //初试化对象
            DefaultAcsClient defaultAcsClient = InitObject.initVodClient(ConstantVideoPropertiesUtil.ACCESS_KEY_ID, ConstantVideoPropertiesUtil.ACCESS_KEY_SECRET);
            //获取request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            DeleteVideoResponse response = defaultAcsClient.getAcsResponse(request);
            return R.ok();
        } catch (ClientException e) {
            e.printStackTrace();
            throw  new AlexException(20001, "删除视频失败");
        }
    }

    /**
    *功能描述 根据ID删除阿里云里面的视屏
    * @author Alex
    * @Date 2021/1/17 20:28
    * @param videoListId
    * @return com.alex.commonutils.R
    */
    @DeleteMapping("deleteBatch")
    public R deleteBatch(@RequestParam("videoListId") List<String> videoListId){
            vodService.removeMoreVideo(videoListId);
        return R.ok();
    }




}
