package com.alex.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CourseQuery
 * @Description TODO :
 * @Author Alex
 * @Date 2020/12/23 22:22
 * @Version 1.0
 */

@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
@Data
public class CourseQuery {
    @ApiModelProperty(value = "课程名称模糊查询")
    private String title;
    @ApiModelProperty(value = "课程状态查询")
    private String status;
}
