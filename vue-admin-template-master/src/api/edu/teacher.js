import request from '@/utils/request'

export default {
    getList(current, limit, teacherQuery) { 
        return request({
            // /eduservice/teacher/pageTeacherCondition/{current}/{limit}
            url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}`,
            method: 'post',
            data: teacherQuery

        })
    },
    addTeacher(eduTeacher) { 
        return request({
            url: `/eduservice/teacher/addTeacher`,
            method: 'post',
            data: eduTeacher
        })
    },
    deleteTeacherById(id) {
        return request({
            url: `/eduservice/teacher/${id}`,
            method: 'delete'
        })
    },
    getTeacherInfo(id) {
        return request({
            url: `/eduservice/teacher/getTeacher/${id}`,
            method: 'get'
        })
     },
    updateTeacher(teacher) { 
        return request({
            url: `/eduservice/teacher/updateTeacher`,
            method: 'post',
            data: teacher
        })
    }
}

