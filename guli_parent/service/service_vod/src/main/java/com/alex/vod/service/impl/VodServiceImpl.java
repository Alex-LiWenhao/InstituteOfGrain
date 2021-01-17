package com.alex.vod.service.impl;

import com.alex.servicebase.AlexException;
import com.alex.vod.service.VodService;
import com.alex.vod.utils.ConstantVideoPropertiesUtil;
import com.alex.vod.utils.InitObject;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName VodServiceImpl
 * @Description TODO : 用于操作video
 * @Author Alex
 * @Date 2020/12/26 21:01
 * @Version 1.0
 */
@Service
public class VodServiceImpl implements VodService {
    /**
    *功能描述 上传视频到阿里云
    * @author Alex
    * @Date 2020/12/26 21:23
    * @param file
    * @return java.lang.String
    */
    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            //上传文件的时候的原始名称
            String fileName = file.getOriginalFilename();
            //title：上传之后显示名称
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            String videoId = null;
            //inputStream：上传文件输入流
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVideoPropertiesUtil.ACCESS_KEY_ID, ConstantVideoPropertiesUtil.ACCESS_KEY_SECRET, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
    *功能描述 根据ID删除阿里云里面的视屏
    * @author Alex
    * @Date 2021/1/17 20:27
    * @param videoListId
    * @return void
    */
    @Override
    public void removeMoreVideo(List<String> videoListId) {
        try {
            //初试化对象
            DefaultAcsClient defaultAcsClient = InitObject.initVodClient(ConstantVideoPropertiesUtil.ACCESS_KEY_ID, ConstantVideoPropertiesUtil.ACCESS_KEY_SECRET);
            //获取request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            String join = StringUtils.join(videoListId.toArray(), ",");
            request.setVideoIds(join);
            defaultAcsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw  new AlexException(20001, "删除视频失败");
        }

    }
}
