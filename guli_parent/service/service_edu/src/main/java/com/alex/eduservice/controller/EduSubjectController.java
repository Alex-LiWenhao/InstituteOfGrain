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

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list =  subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }
}

