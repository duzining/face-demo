package com.du.facedemo.service;

import com.alibaba.fastjson.JSONObject;
import com.du.facedemo.utill.FileUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Du
 * @date 2020/8/22 16:53
 */

@Service
public class QiniuService {

    public static final Logger logger = LoggerFactory.getLogger(QiniuService.class);

    /**
     * ACCESS_KEY 和 SECRET_KEY
     */

    String ACCESS_KEY = "2X8wH_c_e1_PFMY-NY8Op1W-CvhaizbJZ3kAh9Gf";
    String SECRET_KEY = "kI1yZ-8TEuOwMNqBX635Y5mGZrk0AdViteTuMP40";

    /**
     * 要上传的空间
      */
    String bucketname = "du-test";

    /**
     * 密钥配置
      */
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    /**
     * 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
      */
    Configuration cfg = new Configuration(Zone.zone2());
    /**
     * ...其他参数参考类注释
     */
    UploadManager uploadManager = new UploadManager(cfg);

    /**
     * 测试域名，只有30天有效期
     */
    private static String QINIU_IMAGE_DOMAIN = "http://qfgaq9ymp.hn-bkt.clouddn.com/";

    /**
     * 简单上传，使用默认策略，只需要设置上传的空间名就可以了
      */
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public String saveImage(MultipartFile file) throws IOException {
        try {
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
            // 判断是否是合法的文件后缀
            if (!FileUtil.isFileAllowed(fileExt)) {
                return null;
            }

            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            // 调用put方法上传
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            // 打印返回的信息
            if (res.isOK() && res.isJson()) {
                // 返回这张存储照片的地址
                return QINIU_IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
            } else {
                logger.error("七牛异常:" + res.bodyString());
                return null;
            }
        } catch (QiniuException e) {
            // 请求失败时打印的异常的信息
            logger.error("七牛异常:" + e.getMessage());
            return null;
        }
    }
}
