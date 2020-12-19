package com.alex.eduservice.controller;


import com.alex.commonutils.R;
import com.alex.eduservice.entity.chapter.ChapterVo;
import com.alex.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2020-12-01
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService service;

    /**
    *功能描述 根据课程ID来获取章节和小结
    * @author Alex
    * @Date 2020/12/18 17:33
    * @param courseId
    * @return com.alex.commonutils.R
    */
    @GetMapping("getChapterVideo/{id}")
    public R getChapterVideo(@PathVariable("id") String courseId){
       List<ChapterVo> list = service.getChapterVideoByCourseId(courseId);
        return R.ok().data("list",list);
    }

}

