package com.alex.eduservice.service.impl;

import com.alex.eduservice.entity.EduVideo;
import com.alex.eduservice.mapper.EduVideoMapper;
import com.alex.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2020-12-01
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    /**
    *功能描述 根据课程ID删除小节
    * @author Alex
    * @Date 2020/12/24 23:12
    * @param [courseId]
    * @return void
    */
    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
