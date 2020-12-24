package com.alex.eduservice.service;

import com.alex.eduservice.entity.EduCourse;
import com.alex.eduservice.entity.vo.CourseInfoVo;
import com.alex.eduservice.entity.vo.CoursePublishVo;
import com.alex.eduservice.entity.vo.CourseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Alex
 * @since 2020-12-01
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
    *功能描述 保存课程信息
    * @author Alex
    * @Date 2020/12/18 23:02
    * @param courseInfoVo
    * @return void
    */
    String saveCourseInfo(CourseInfoVo courseInfoVo);
    /**
    *功能描述 根据课程ID获取到课程
    * @author Alex
    * @Date 2020/12/19 15:05
    * @param courseId
    * @return com.alex.eduservice.entity.vo.CourseInfoVo
    */
    CourseInfoVo getCourseInfo(String courseId);
    /**
    *功能描述 修改course和简介
    * @author Alex
    * @Date 2020/12/19 15:05
    * @param courseInfoVo
    * @return void
    */
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    /**
    *功能描述 根据课程ID获取道发布信息
    * @author Alex
    * @Date 2020/12/23 11:22
    * @param courseId
    * @return com.alex.eduservice.entity.vo.CoursePublishVo
    */
    public CoursePublishVo selectCoursePublishVoById(String courseId);

    /**
    *功能描述 课程条件查询模块
    * @author Alex
    * @Date 2020/12/23 22:34
    * @param [page, courseQuery]
    * @return void
    */
    void courseQuery(Page<EduCourse> page, CourseQuery courseQuery);

    /**
    *功能描述 删除课程相关的内容
    * @author Alex
    * @Date 2020/12/24 23:02
    * @param [courseId]
    * @return void
    */
    void removeCourse(String courseId);
}
