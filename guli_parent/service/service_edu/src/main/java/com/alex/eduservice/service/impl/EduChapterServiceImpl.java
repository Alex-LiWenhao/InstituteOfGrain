package com.alex.eduservice.service.impl;

import com.alex.eduservice.entity.EduChapter;
import com.alex.eduservice.entity.EduVideo;
import com.alex.eduservice.entity.chapter.ChapterVo;
import com.alex.eduservice.entity.chapter.VideoVo;
import com.alex.eduservice.mapper.EduChapterMapper;
import com.alex.eduservice.service.EduChapterService;
import com.alex.eduservice.service.EduVideoService;
import com.alex.servicebase.AlexException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2020-12-01
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;

    /**
    *功能描述 根据课程ID获取章节和小结
    * @author Alex
    * @Date 2020/12/18 17:44
    * @param courseId
    * @return java.util.List<com.alex.eduservice.entity.vo.CourseInfoVo>
    */
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        List<ChapterVo> finalList = new ArrayList<>();
        QueryWrapper<EduChapter> chapterwrapper = new QueryWrapper<>();
        chapterwrapper.eq("course_id", courseId);
        chapterwrapper.orderByAsc("sort", "id");
        List<EduChapter> chapters = baseMapper.selectList(chapterwrapper);

        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id", courseId);
        videoWrapper.orderByAsc("sort","id");
        List<EduVideo> videos = videoService.list(videoWrapper);

        for (int i = 0; i < chapters.size(); i++) {
            ChapterVo chapterVo = new ChapterVo();
            EduChapter eduChapter = chapters.get(i);
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalList.add(chapterVo);

            //创建集合，用于封装章节的小节
            List<VideoVo> videoList = new ArrayList<>();
            for (int j = 0; j < videos.size(); j++) {
                EduVideo eduVideo = videos.get(j);
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoList.add(videoVo);
                }
                //把封装之后小节list集合，放到章节对象里面
                chapterVo.setVideo(videoList);
            }


        }
        return finalList;
    }

    /**
    *功能描述 根据ID删除章节
    * @author Alex
    * @Date 2020/12/20 14:19
    * @param
    * @return
    */
    @Override
    public boolean deleteChapterById(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if(count>0){
            throw new AlexException(20001, "不能删除");
        }else {
            int i = baseMapper.deleteById(chapterId);
            return i>0;
        }
    }
}
