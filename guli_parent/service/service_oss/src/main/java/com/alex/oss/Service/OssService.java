package com.alex.oss.Service;

import com.alex.oss.utils.ConstantPropertiesUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName OssService
 * @Description TODO :
 * @Author Alex
 * @Date 2020/11/24 10:48
 * @Version 1.0
 */
@Service
public class OssService {
    /**
    *功能描述 上传文件到OSS云存储器
    * @author Alex
    * @Date 2020/12/18 22:59
    * @param file
    * @return java.lang.String
    */
    public String uploadFileAvatar(MultipartFile file){
        String endpoint = ConstantPropertiesUtil.EDN_POINT;
        String keyid = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String keysecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String  bucketname = ConstantPropertiesUtil.BUCKET_NAME;

        try {
            //创建实例
            OSS oss = new OSSClientBuilder().build(endpoint, keyid, keysecret);
            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String filename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            filename = uuid+filename;
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            filename = format.format(date)+"/"+filename;

            //调用OSS实现文件的上传
            //第一个参数是bucketname
            //第二个参数是上传文件路径和名称
            //第三个参数是上传文件输入流
            oss.putObject(bucketname, filename, inputStream);
            //关闭OSS
            oss.shutdown();
            //吧上传的文件路径返回到前台
            //https://edu-alex.oss-cn-chengdu.aliyuncs.com/v2-003430cdbc62c07d2c62325ca865235c_xl.jpg
            String url = "https://"+bucketname+"."+endpoint+"/"+filename;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
