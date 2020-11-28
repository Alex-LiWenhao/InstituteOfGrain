package com.alex.eduservice;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EasyExcelTest
 * @Description TODO :
 * @Author Alex
 * @Date 2020/11/28 15:41
 * @Version 1.0
 */
public class EasyExcelTest {
    /*public static void main(String[] args) {
        String filename = "d:\\easy.xlsx";
        EasyExcel.write(filename,DemoData.class).sheet("学生信息")
                .doWrite(getData());
    }*/
    public static void main(String[] args) {
        String filename = "d:\\easy.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filename,ReadData.class, new ExcelListener())
                .sheet().doRead();

    }

    public static List<DemoData> getData(){
        List<DemoData> data = new ArrayList<>();
        for (int i=0; i < 5; i++){
            data.add(new DemoData(i, "alex" + i));
        }
        return data;
    }
}
