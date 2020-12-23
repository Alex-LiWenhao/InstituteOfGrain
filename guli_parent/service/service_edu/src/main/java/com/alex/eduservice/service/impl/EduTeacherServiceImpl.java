package com.alex.eduservice.service.impl;

import com.alex.eduservice.entity.EduTeacher;
import com.alex.eduservice.entity.vo.TeacherQuery;
import com.alex.eduservice.mapper.EduTeacherMapper;
import com.alex.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2020-11-08
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    /**
    *功能描述 条件查询讲师
    * @author Alex
    * @Date 2020/12/18 23:08
    * @param pageParam, teacherQuery
    * @return void
    */
    @Override
    public void teacherQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        if (teacherQuery == null){
            baseMapper.selectMapsPage(pageParam, queryWrapper);
            return ;
        }

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)){
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create", "begin");
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_modified", end);
        }
        baseMapper.selectPage(pageParam, queryWrapper);

    }
}
