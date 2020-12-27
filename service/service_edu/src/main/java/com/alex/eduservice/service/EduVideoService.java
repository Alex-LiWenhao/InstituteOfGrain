package com.alex.eduservice.service;

import com.alex.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Alex
 * @since 2020-12-01
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
    *功能描述 根据课程ID删除小节
    * @author Alex
    * @Date 2020/12/24 23:11
    * @param [courseId]
    * @return void
    */
    void removeVideoByCourseId(String courseId);
}
