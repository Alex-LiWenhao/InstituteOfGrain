import request from '@/utils/request'
const api_name = '/eduservice/course/addCourseInfo'
export default {
    addCourseInfo(courseInfo) {
        return request({
            url: `${api_name}`,
            method: 'post',
            data: courseInfo
        })
    },
    getListTeacher() { 
        return request({
            url: `/eduservice/teacher/`,
            method: 'get'
        })
    }
}