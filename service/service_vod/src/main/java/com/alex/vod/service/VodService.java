package com.alex.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
*功能描述 操作video的接口
* @author Alex
* @Date 2020/12/26 21:06
* @param
* @return
*/
public interface VodService {
    /**
    *功能描述 上传视频到阿里云
    * @author Alex
    * @Date 2020/12/26 21:23
    * @param file
    * @return java.lang.String
    */
    String uploadVideo(MultipartFile file);
}
