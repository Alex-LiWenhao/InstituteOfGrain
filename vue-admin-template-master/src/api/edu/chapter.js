import request from '@/utils/request'
export default {
    //根据课程ID去查询章节和小结
    getNestedTreeList(courseId) { 
        return request({
            url: `/eduservice/chapter/getChapterVideo/${courseId}`,
            method: 'get'
        }) 
    },
    //删除章节通过章节ID
    removeById(chapterId){
         return request({
            url: `/eduservice/chapter/${chapterId}`,
            method: 'delete'
        }) 
    },
    //添加章节信息
    save(chapter){
        return request({
            url: `/eduservice/chapter/addChapter`,
            method: 'post',
            data: chapter
        }) 
    },
    //根据章节ID去修改章节信息
    updateChapter(chapter){
        return request({
            url: `/eduservice/chapter/updateChapter`,
            method: 'put',
            data: chapter
        })
    },
    //根据ID去查询章节信息返回
    getChapterById(chapterId){
        return request({
            url: `/eduservice/chapter/${chapterId}`,
            method: 'get'
        })
    }




}