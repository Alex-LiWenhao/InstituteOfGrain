package com.alex.eduservice.service.impl;

import com.alex.eduservice.entity.EduSubject;
import com.alex.eduservice.entity.excel.SubjectData;
import com.alex.eduservice.entity.subject.OneSubject;
import com.alex.eduservice.entity.subject.TwoSubject;
import com.alex.eduservice.listener.SubjectExcelListener;
import com.alex.eduservice.mapper.EduSubjectMapper;
import com.alex.eduservice.service.EduSubjectService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2020-11-29
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
    *功能描述 读取Excel里面内容
    * @author Alex
    * @Date 2020/12/18 23:08
    * @param [file, subjectService]
    * @return void
    */
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            //eduSubject值复制到对应oneSubject对象里面
            BeanUtils.copyProperties(eduSubject, oneSubject);
            finalSubjectList.add(oneSubject);
            List<TwoSubject> twoSubjectArrayList = new ArrayList<>();

            for (int j = 0; j < twoSubjectList.size(); j++) {
                EduSubject eduTwoSubject = twoSubjectList.get(j);
                if (eduTwoSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduTwoSubject, twoSubject);
                    twoSubjectArrayList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoSubjectArrayList);
        }

        return finalSubjectList;
    }
}
