package com.alex.eduservice.controller;


import com.alex.commonutils.R;
import com.alex.eduservice.entity.EduChapter;
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


    /**
    *功能描述 添加章节的操作
    * @author Alex
    * @Date 2020/12/20 13:58
    * @param chapter
    * @return com.alex.commonutils.R
    */
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter chapter){
        service.save(chapter);
        return R.ok();
    }

    /**
    *功能描述 根据章节ID去查询章节并返回给前端
    * @author Alex
    * @Date 2020/12/20 14:01
    * @param id
    * @return com.alex.commonutils.R
    */
    @GetMapping("{id}")
    public R getChapterById(@PathVariable String id){
        EduChapter eduChapter = service.getById(id);
        return R.ok().data("eduChapter",eduChapter);
    }

    /**
    *功能描述 根据ID去修改章节
    * @author Alex
    * @Date 2020/12/20 14:09
    * @param eduChapter
    * @return com.alex.commonutils.R
    */
    @PutMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        service.updateById(eduChapter);
        return R.ok();
    }
    /**
    *功能描述
    * @author Alex
    * @Date 2020/12/20 17:15
    * @param chapterId
    * @return com.alex.commonutils.R
    */
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean flag = service.deleteChapterById(chapterId);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }




}

