import request from '@/utils/request'
export default {
    getNestedTreeList(courseId) { 
        return request({
            url: `/eduservice/chapter/getChapterVideo/${courseId}`,
            method: 'get'
        }) 
    }
}