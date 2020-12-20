package com.alex.eduservice.listener;

import com.alex.eduservice.entity.EduSubject;
import com.alex.eduservice.entity.excel.SubjectData;
import com.alex.eduservice.service.EduSubjectService;
import com.alex.servicebase.AlexException;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @ClassName SubjectExcelListener
 * @Description TODO :用于课程的xlsl的读取然后保存到数据库
 * @Author Alex
 * @Date 2020/11/29 20:45
 * @Version 1.0
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    private EduSubjectService subjectService;
    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
    *功能描述 读取excel内容，一行一行进行读取
    * @author Alex
    * @Date 2020/11/29 20:45
    * @param subjectData, analysisContext
    * @return void
    */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null){
            throw new AlexException(20001, "数据为空");
        }
        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断一级分类是否重复
        EduSubject oneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (oneSubject==null){
            oneSubject = new EduSubject();
            oneSubject.setTitle(subjectData.getOneSubjectName());
            oneSubject.setParentId("0");
            subjectService.save(oneSubject);
        }
        String pid = oneSubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        EduSubject twoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (twoSubject==null){
            twoSubject = new EduSubject();
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            twoSubject.setParentId(pid);
            subjectService.save(twoSubject);
        }

    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService, String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.ne("parent_id", pid);

        EduSubject eduSubject = subjectService.getOne(wrapper);
        return eduSubject;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
