package com.du.facedemo.service;

import com.alibaba.fastjson.JSON;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.VerifyFaceRequest;
import com.tencentcloudapi.iai.v20200303.models.VerifyFaceResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Du
 * @date 2020/8/22 20:50
 */
@Service
public class VerifyFaceService {

    public void verifyFace(String imgBase4,String imgUrl){

        try{

            Credential cred = new Credential();

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-guangzhou", clientProfile);

            Map<String,Object> map = new HashMap<>();
            map.put("PersonId","du_01");
            map.put("Image",imgBase4);
            map.put("Url",imgUrl);

            String params = JSON.toJSONString(map);
            VerifyFaceRequest req = VerifyFaceRequest.fromJsonString(params, VerifyFaceRequest.class);

            VerifyFaceResponse resp = client.VerifyFace(req);

            System.out.println(VerifyFaceResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }
}
