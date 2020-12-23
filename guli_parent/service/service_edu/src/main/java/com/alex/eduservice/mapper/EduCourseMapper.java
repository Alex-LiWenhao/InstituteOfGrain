package com.alex.eduservice.mapper;

import com.alex.eduservice.entity.EduCourse;
import com.alex.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Alex
 * @since 2020-12-01
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    /**
    *功能描述 根据课程ID获取道发布信息
    * @author Alex
    * @Date 2020/12/23 9:40
    * @param courseId
    * @return com.alex.eduservice.entity.vo.CoursePublishVo
    */
    public CoursePublishVo selectCoursePublishVoById(@Param("courseId") String courseId);

}
