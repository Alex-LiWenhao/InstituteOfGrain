package com.alex.eduservice.entity.chapter;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChapterVo
 * @Description TODO :
 * @Author Alex
 * @Date 2020/12/18 16:49
 * @Version 1.0
 */
@Data
public class ChapterVo implements Serializable {
    private String id;
    private String title;
    private List<VideoVo> video =  new ArrayList<>();


}
