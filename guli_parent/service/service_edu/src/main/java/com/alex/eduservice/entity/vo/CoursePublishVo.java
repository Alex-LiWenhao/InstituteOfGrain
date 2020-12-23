package com.alex.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CoursePublishVo
 * @Description TODO :
 * @Author Alex
 * @Date 2020/12/23 9:30
 * @Version 1.0
 */
@ApiModel(value ="课程发布信息")
@Data
public class CoursePublishVo implements Serializable {
    private String id;
    //课程名称
    private String title;
    //课程封面
    private String cover;
    //课程总课时
    private Integer lessonNum;
    //一级分类
    private String subjectLevelOne;
    //二级分类
    private String subjectLevelTwo;
    //讲师姓名
    private String teacherName;
    //课程价格
    private String price;//只用于显示
}
