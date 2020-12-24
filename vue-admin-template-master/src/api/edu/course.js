import request from '@/utils/request'
const api_name = '/eduservice/course/addCourseInfo'
export default {
    //添加课程
    addCourseInfo(courseInfo) {
        return request({
            url: `${api_name}`,
            method: 'post',
            data: courseInfo
        })
    },
    //查询所有讲师
    getListTeacher() { 
        return request({
            url: `/eduservice/teacher/`,
            method: 'get'
        })
    },
    //根具ID查询课程
    getCourseInfo(courseId) {
        return request({
            url: `/eduservice/course/getCourseInfo/${courseId}`,
            method: 'get'
        })
    },
    //修改课程和简介信息
    updateCourseInfo(courseInfo) { 
        return request({
            url: `/eduservice/course/updateCourseInfo`,
            method: 'post',
            data: courseInfo
        })
    },
    //查询发布信息
    getCoursePublishInfoById(id) {
        return request({
            url: `/eduservice/course/${id}`,
            method: 'get'
        })
    },
    //课程最终发布
    publishCourse(id) {
        return request({
            url: `/eduservice/course/publishCourse/${id}`,
            method: 'put'
        })
    },
    //分页查询所有课程
    findAll(current, limit, courseQuery) { 
        return request({
            url: `/eduservice/course/pageCourseCondition/${current}/${limit}`,
            method: 'post',
            data: courseQuery
        })
    },
    //根据课程ID删除课程相关内容
    removeCourseByCourseId(courseId) { 
        return request({
            url: `/eduservice/course/${courseId}`,
            method: 'delete'
        })
    },
}