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
    }

}