package com.alex.eduservice.controller;


import com.alex.commonutils.R;
import com.alex.eduservice.entity.EduVideo;
import com.alex.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2020-12-01
 */
@RestController
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {

    @Autowired(required = true)
    private EduVideoService videoService;

    /**
    *功能描述 添加小节
    * @author Alex
    * @Date 2020/12/20 23:14
    * @param video
    * @return com.alex.commonutils.R
    */
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo video){
        videoService.save(video);
        return R.ok();
    }


    /**
    *功能描述 删除小节TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
    * @author Alex
    * @Date 2020/12/20 23:15
    * @param id
    * @return com.alex.commonutils.R
    */
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        videoService.removeById(id);
        return R.ok();
    }

    /**
    *功能描述 修改小结内容
    * @author Alex
    * @Date 2020/12/20 23:18
    * @param video
    * @return com.alex.commonutils.R
    */
    @PutMapping("updateVideo")
    public R updateVideo(@PathVariable EduVideo video){
        videoService.updateById(video);
        return R.ok();
    }
    

}

