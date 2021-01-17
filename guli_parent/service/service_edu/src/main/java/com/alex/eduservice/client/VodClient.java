package com.alex.eduservice.client;

import com.alex.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName VodClient
 * @Description TODO :
 * @Author Alex
 * @Date 2021/1/17 19:33
 * @Version 1.0
 */
@Component
@FeignClient(name = "service-vod")
public interface VodClient {
    /**
    *功能描述 根据视屏ID删除阿里云里面的视屏
    * @author Alex
    * @Date 2021/1/17 21:43
    * @param id
    * @return com.alex.commonutils.R
    */
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public R deleteAlyVideo(@PathVariable("id") String id);

    /**
    *功能描述 根据视屏集合去删除阿里云里面所有小结的视屏
    * @author Alex
    * @Date 2021/1/17 21:43
    * @param videoListId
    * @return com.alex.commonutils.R
    */
    @DeleteMapping("/eduvod/video/deleteBatch")
    public R deleteBatch(@RequestParam("videoListId") List<String> videoListId);
}
