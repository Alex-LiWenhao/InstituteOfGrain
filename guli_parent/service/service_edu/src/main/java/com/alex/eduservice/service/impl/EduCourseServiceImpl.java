package com.alex.eduservice.service.impl;

import com.alex.eduservice.entity.EduCourse;
import com.alex.eduservice.entity.EduCourseDescription;
import com.alex.eduservice.entity.vo.CourseInfoVo;
import com.alex.eduservice.mapper.EduCourseMapper;
import com.alex.eduservice.service.EduCourseDescriptionService;
import com.alex.eduservice.service.EduCourseService;
import com.alex.servicebase.AlexException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private EduCourseDescriptionService descriptionService;

    @Override
    public void saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert==0){
            throw new AlexException(20001, "插入失败");
        }
        String cid = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(cid);
        descriptionService.save(eduCourseDescription);
    }
}
