package com.alex.eduservice.service;

import com.alex.eduservice.entity.EduCourse;
import com.alex.eduservice.entity.vo.CourseInfoVo;
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

    void saveCourseInfo(CourseInfoVo courseInfoVo);
}
