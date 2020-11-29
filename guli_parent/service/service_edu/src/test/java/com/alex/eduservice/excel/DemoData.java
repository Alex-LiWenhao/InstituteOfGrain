package com.alex.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DemoDate
 * @Description TODO :
 * @Author Alex
 * @Date 2020/11/28 15:43
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoData {
    @ExcelProperty(value = "学生编号")
    private Integer sno;
    @ExcelProperty(value = "学生姓名")
    private String sname;
}
