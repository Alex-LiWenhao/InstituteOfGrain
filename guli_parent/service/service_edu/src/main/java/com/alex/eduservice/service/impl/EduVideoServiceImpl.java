package com.alex.eduservice.service.impl;

import com.alex.eduservice.client.VodClient;
import com.alex.eduservice.entity.EduVideo;
import com.alex.eduservice.mapper.EduVideoMapper;
import com.alex.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    //更多视频删除注入
    @Autowired
    private VodClient vodClient;
    /**
    *功能描述 根据课程ID删除小节
    * @author Alex
    * @Date 2020/12/24 23:12
    * @param courseId
    * @return void
    */
    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(queryWrapper);
        List<String> list = new ArrayList<>();
        for (EduVideo eduVideo : eduVideos) {
            if (!StringUtils.isEmpty(eduVideo.getVideoSourceId())){
                list.add(eduVideo.getVideoSourceId());
            }
        }
        if (list.size()>0){
            vodClient.deleteBatch(list);
        }
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
