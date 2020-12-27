package com.alex.eduservice.service;

import com.alex.eduservice.entity.EduChapter;
import com.alex.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Alex
 * @since 2020-12-01
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapterById(String chapterId);

    /**
    *功能描述 根据课程ID删除章节
    * @author Alex
    * @Date 2020/12/24 23:13
    * @param [courseId]
    * @return void
    */
    void removeChapterByCourseId(String courseId);
}
