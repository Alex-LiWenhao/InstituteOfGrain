package com.alex.eduservice.service.impl;

import com.alex.eduservice.client.VodClient;
import com.alex.eduservice.entity.EduCourse;
import com.alex.eduservice.entity.EduCourseDescription;
import com.alex.eduservice.entity.EduVideo;
import com.alex.eduservice.entity.vo.CourseInfoVo;
import com.alex.eduservice.entity.vo.CoursePublishVo;
import com.alex.eduservice.entity.vo.CourseQuery;
import com.alex.eduservice.mapper.EduCourseMapper;
import com.alex.eduservice.service.EduChapterService;
import com.alex.eduservice.service.EduCourseDescriptionService;
import com.alex.eduservice.service.EduCourseService;
import com.alex.eduservice.service.EduVideoService;
import com.alex.servicebase.AlexException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程 服务实现
 * </p>
 *
 * @author Alex
 * @since 2020-12-01
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //课程描述注入
    @Autowired
    private EduCourseDescriptionService descriptionService;
    //课程小节注入
    @Autowired
    private EduVideoService eduVideoService;
    //课程章节注入
    @Autowired
    private EduChapterService eduChapterService;



    /**
    *功能描述 添加课程基本信息的方法
    * @author Alex
    * @Date 2020/12/18 23:09
    * @param courseInfoVo
    * @return void
    */
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert==0){
            throw new AlexException(20001, "插入失败");
        }
        //获取添加之后课程id
        String cid = eduCourse.getId();
        //2 向课程简介表添加课程简介
        //edu_course_description
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(cid);
        descriptionService.save(eduCourseDescription);
        return cid;
    }

    /**
    *功能描述 根据课程id查询课程基本信息
    * @author Alex
    * @Date 2020/12/19 14:38
    * @param courseId
    * @return com.alex.eduservice.entity.EduCourse
    */
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        EduCourseDescription byId = descriptionService.getById(courseId);
        courseInfoVo.setDescription(byId.getDescription());
        return courseInfoVo;
    }

    /**
    *功能描述 修改课程信息和简介信息
    * @author Alex
    * @Date 2020/12/19 15:06
    * @param
    * @return
    */
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if(i==0){
            throw new AlexException(20001, "修改失败");
        }
        //2 修改描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(courseInfoVo.getId());
        descriptionService.updateById(eduCourseDescription);
    }

    /**
    *功能描述 根据课程ID获取道发布信息
    * @author Alex
    * @Date 2020/12/23 11:23
    * @param courseId
    * @return com.alex.eduservice.entity.vo.CoursePublishVo
    */
    @Override
    public CoursePublishVo selectCoursePublishVoById(String courseId) {
        CoursePublishVo coursePublishVo = baseMapper.selectCoursePublishVoById(courseId);
        return coursePublishVo;
    }

    /**
    *功能描述 课程条件查询模块
    * @author Alex
    * @Date 2020/12/23 22:40
    * @param page, courseQuery
    * @return void
    */
    @Override
    public void courseQuery(Page<EduCourse> page, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        if (courseQuery==null){
            baseMapper.selectMapsPage(page, wrapper);
            return;
        }
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        if(!StringUtils.isEmpty(title)){
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)){
            wrapper.eq("status", status);
        }

        baseMapper.selectPage(page, wrapper);
    }

    /**
    *功能描述 删除课程相关的内容
    * @author Alex
    * @Date 2020/12/24 23:17
    * @param courseId
    * @return void
    */
    @Override
    public void removeCourse(String courseId) {
        //根据课程ID删除小节
        eduVideoService.removeVideoByCourseId(courseId);
        //根据课程ID删除章节
        eduChapterService.removeChapterByCourseId(courseId);
        //根据课程ID删除简介
        descriptionService.removeById(courseId);
        //根据课程ID删除课程本身
        int i = baseMapper.deleteById(courseId);
        if (i==0){
            throw new AlexException(20001, "删除失败");
        }
    }
}
