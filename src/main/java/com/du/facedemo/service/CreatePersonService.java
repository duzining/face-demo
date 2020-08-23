package com.du.facedemo.service;

import com.alibaba.fastjson.JSON;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.CreatePersonRequest;
import com.tencentcloudapi.iai.v20200303.models.CreatePersonResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Du
 * @date 2020/8/22 19:38
 */
@Service
public class CreatePersonService {

    public void createPerson(String imgBase4,String imgUrl){
        try{

            Credential cred = new Credential();

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-guangzhou", clientProfile);

            Map<String,Object> map = new HashMap<>();
            map.put("GroupId","du_test_01");
            map.put("PersonName","du");
            map.put("PersonId","du_01");
            map.put("Gender",1);

            List<Map<String,Object>> list = new ArrayList<>();

            Map<String,Object> map1 = new HashMap<>();
            map1.put("PersonExDescriptionIndex",0);
            map1.put("PersonExDescription","440304199910204120");
            list.add(map1);
            Map<String,Object> map2 = new HashMap<>();
            map2.put("PersonExDescriptionIndex",1);
            map2.put("PersonExDescription","4");
            list.add(map2);
            Map<String,Object> map3 = new HashMap<>();
            map3.put("PersonExDescriptionIndex",2);
            map3.put("PersonExDescription","粤66666");
            list.add(map3);

            map.put("PersonExDescriptionInfos",list);
            map.put("Image",imgBase4);
            map.put("Url",imgUrl);


            String params = JSON.toJSONString(map);
            CreatePersonRequest req = CreatePersonRequest.fromJsonString(params, CreatePersonRequest.class);

            CreatePersonResponse resp = client.CreatePerson(req);

            /**
             * {"FaceId":"3783092176548831146","FaceRect":{"X":184,"Y":78,"Width":159,"Height":219},"SimilarPersonId":"","FaceModelVersion":"3.0","RequestId":"2e6c5271-8c14-4139-acd5-4d9a36eebccb"}
             */
            System.out.println(CreatePersonResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}
