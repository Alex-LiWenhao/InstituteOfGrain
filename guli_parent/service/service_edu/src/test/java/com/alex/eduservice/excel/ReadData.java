package com.alex.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ReadData
 * @Description TODO :
 * @Author Alex
 * @Date 2020/11/28 15:57
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadData {
    @ExcelProperty(index = 0)
    private Integer sid;

    @ExcelProperty(index = 1)
    private String sname;
}
