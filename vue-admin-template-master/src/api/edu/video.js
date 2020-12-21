import request from '@/utils/request'
export default {
    //新增小节
    addVideo(video) { 
        return request({
            url: `/eduservice/video/addVideo`,
            method: 'post',
            data: video
        }) 
    },
    //修改小结
    updateVideo(video) { 
        return request({
            url: `/eduservice/video/updateVideo`,
            method: 'put',
            data: video
        })
    },
    //根据VideoID去删除video
    deleteVideo(videoId) { 
        return request({
            url: `/eduservice/video/${videoId}`,
            method: 'delete'
        })
    }
}