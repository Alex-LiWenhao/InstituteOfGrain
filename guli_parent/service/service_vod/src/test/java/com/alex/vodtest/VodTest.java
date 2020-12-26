package com.alex.vodtest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import lombok.SneakyThrows;

import java.util.List;

/**
 * @ClassName VodTest
 * @Description TODO :
 * @Author Alex
 * @Date 2020/12/26 20:16
 * @Version 1.0
 */
public class VodTest {

    @SneakyThrows
    public static void main(String[] args) {
        //1.初始化对象
        DefaultAcsClient client = InitObject.initVodClient("yourAccessKeyId", "yourAccessKeySecret");
        //2.创建获取视频的地址request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        //3。向request里面设置视频ID
        request.setVideoId("176255f90ce94f27843438f29c6aa997");
        //4.调用初始化对象里面的方法，传递request获取数据
        GetVideoPlayAuthResponse acsResponse = client.getAcsResponse(request);
        System.out.println(acsResponse.getPlayAuth()+"");

    }

    @SneakyThrows
    public  static void  getVideoUrl(){
        //1.初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI4G9xTj2weZ4gXDCYFSkk", "LsQlcuwEXtQO0YU3ltHFklvbNjger0");
        //2.创建获取视频的地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //3。向request里面设置视频ID
        request.setVideoId("176255f90ce94f27843438f29c6aa997");
        //4.调用初始化对象里面的方法，传递request获取数据
        GetPlayInfoResponse acsResponse = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = acsResponse.getPlayInfoList();
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print(playInfo.getPlayURL()+"\n");

        }
    }
}
