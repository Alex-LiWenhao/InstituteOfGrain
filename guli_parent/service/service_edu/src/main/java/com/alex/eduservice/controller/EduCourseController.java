package com.alex.eduservice.controller;


import com.alex.commonutils.R;
import com.alex.eduservice.entity.vo.CourseInfoVo;
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
    @Autowired
    private EduCourseService eduCourseService;


    @ApiOperation(value = "新增课程")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加之后课程id，为了后面添加大纲使用
        eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok();
    }
}

