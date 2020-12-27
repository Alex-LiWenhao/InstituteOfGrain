package com.alex.eduservice.controller;



import com.alex.commonutils.R;
import com.alex.eduservice.entity.EduTeacher;
import com.alex.eduservice.entity.vo.TeacherQuery;
import com.alex.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2020-11-08
 */
@Api(description = "讲师管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    /**
    *功能描述 查询所有讲师列表
    * @author Alex
    * @Date 2020/12/18 23:04
    * @param []
    * @return com.alex.commonutils.R
    */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping()
    public R findAll(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    /**
    *功能描述 根据讲师ID去删除讲师
    * @author Alex
    * @Date 2020/12/18 23:04
    * @param [id]
    * @return com.alex.commonutils.R
    */
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name ="id",value = "讲师ID", required = true)
            @PathVariable String id){
        boolean b = teacherService.removeById(id);
        return R.ok();
    }

    /**
    *功能描述 根据传送过来的每页记录数和当前页码进行查询
    * @author Alex
    * @Date 2020/12/18 23:04
    * @param page, limit
    * @return com.alex.commonutils.R
    */
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(@ApiParam(name = "page", value = "当前页码", required = true)
                          @PathVariable Long page,
                      @ApiParam(name = "limit", value = "每页记录数", required = true)
                          @PathVariable Long limit){
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        teacherService.page(teacherPage, null);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();

        return R.ok().data("total", total).data("rows",records);
    }

    /**
    *功能描述  根据前端传送过来的条件进行条件查询
    * @author Alex
    * @Date 2020/12/18 23:05
    * @param [current, limit, teacherQuery]
    * @return com.alex.commonutils.R
    */
    @ApiOperation(value = "讲师条件查询模块")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(
                                  @ApiParam(value = "当前页数")
                                  @PathVariable Long current,
                                  @ApiParam(value = "每页多少行数据")
                                  @PathVariable Long limit,
                                  @ApiParam(value = "条件查询内容")
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        teacherService.teacherQuery(teacherPage, teacherQuery);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    /**
    *功能描述 新增讲师
    * @author Alex
    * @Date 2020/12/18 23:06
    * @param eduTeacher
    * @return com.alex.commonutils.R
    */
    @ApiOperation(value = "讲师新增模块")
    @PostMapping("addTeacher")
    public R addTeacher(@ApiParam(name = "eduTeacher",value = "传入对象",required = true)
                  @RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }

    }

    /**
    *功能描述 根据ID查询讲师
    * @author Alex
    * @Date 2020/12/18 23:06
    * @param [id]
    * @return com.alex.commonutils.R
    */
    @ApiOperation(value = "根据ID查讲师")
    @GetMapping("getTeacher/{id}")
    public R getById(
            @ApiParam(value = "要查询讲师的ID")
            @PathVariable Long id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    /**
    *功能描述 修改讲师
    * @author Alex
    * @Date 2020/12/18 23:07
    * @param [eduTeacher]
    * @return com.alex.commonutils.R
    */
    @PostMapping("updateTeacher")
    public R updateTeacher(
            @ApiParam(name = "eduTeacher",value = "要修改内容", required = true)
            @RequestBody
            EduTeacher eduTeacher){
        boolean b = teacherService.updateById(eduTeacher);
        if (b){
            return R.ok().message("修改成功");
        }else {
            return R.error().message("修改失败");
        }
    }




}

