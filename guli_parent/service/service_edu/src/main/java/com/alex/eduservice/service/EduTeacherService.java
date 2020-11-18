package com.alex.eduservice.service;

import com.alex.eduservice.entity.EduTeacher;
import com.alex.eduservice.entity.vo.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Alex
 * @since 2020-11-08
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void teacherQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

}
