package com.alex.vod.service.impl;

import com.alex.vod.service.VodService;
import com.alex.vod.utils.ConstantVideoPropertiesUtil;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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
}
