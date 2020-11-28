package com.alex.eduservice;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExcelListener
 * @Description TODO :
 * @Author Alex
 * @Date 2020/11/28 15:56
 * @Version 1.0
 */
public class ExcelListener extends AnalysisEventListener<ReadData> {
  List<ReadData>  list =  new ArrayList<>();
    @Override
    public void invoke(ReadData readData, AnalysisContext analysisContext) {
        System.out.println("****"+readData);
        list.add(readData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息"+headMap);
    }

}
