import request from '@/utils/request'
const api_name = '/eduservice/subject/getAllSubject'
export default {
    getNestedTreeList() {
        return request({
            url: `${api_name}`,
            method: 'get'
        })
    }
}