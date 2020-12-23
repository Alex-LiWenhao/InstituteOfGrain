package com.alex.eduservice.controller;


import com.alex.commonutils.R;
import com.alex.eduservice.entity.EduCourse;
import com.alex.eduservice.entity.vo.CourseInfoVo;
import com.alex.eduservice.entity.vo.CoursePublishVo;
import com.alex.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2020-12-01
 */
@Api(value = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired(required = true)
    private EduCourseService eduCourseService;


    /**
    *功能描述 添加课程
    * @author Alex
    * @Date 2020/12/18 23:01
    * @param courseInfoVo
    * @return com.alex.commonutils.R
    */
    @ApiOperation(value = "新增课程")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加之后课程id，为了后面添加大纲使用
       String courseId = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",courseId);
    }

    /**
    *功能描述 根据课程ID获取到课程
    * @author Alex
    * @Date 2020/12/19 14:37
    * @param courseId
    * @return com.alex.commonutils.R
    */
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable("courseId") String courseId){
         CourseInfoVo courseInfo = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfo",courseInfo);
    }

    /**
    *功能描述 修改课程内容和简介内容
    * @author Alex
    * @Date 2020/12/19 16:46
    * @param courseInfoVo
    * @return com.alex.commonutils.R
    */
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    /**
    *功能描述 根据课程ID获取道发布信息
    * @author Alex
    * @Date 2020/12/23 11:24
    * @param courseId
    * @return com.alex.commonutils.R
    */
    @GetMapping("{courseId}")
    public R selectCoursePublishVoById(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = eduCourseService.selectCoursePublishVoById(courseId);
        return R.ok().data("coursePublishVo",coursePublishVo);
    }

    /**
    *功能描述 课程最终发布和修改状态
    * @author Alex
    * @Date 2020/12/23 13:44
    * @param id
    * @return com.alex.commonutils.R
    */
    @PutMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }
}

