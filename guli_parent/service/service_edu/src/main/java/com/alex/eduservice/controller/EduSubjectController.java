package com.alex.eduservice.controller;


import com.alex.commonutils.R;
import com.alex.eduservice.entity.subject.OneSubject;
import com.alex.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2020-11-29
 */
@Api(description = "课程分类管理")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    /**
    *功能描述 通过Excel文件添加课程分类
    * @author Alex
    * @Date 2020/12/18 22:58
    * @param file
    * @return com.alex.commonutils.R
    */
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    /**
    *功能描述 得到所有课程
    * @author Alex
    * @Date 2020/12/18 22:59
    * @param
    * @return com.alex.commonutils.R
    */
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list =  subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }
}

