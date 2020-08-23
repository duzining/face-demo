package com.du.facedemo.person;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.CreateGroupRequest;
import com.tencentcloudapi.iai.v20200303.models.CreateGroupResponse;

/**
 * @author Du
 * @date 2020/8/22 19:11
 */
public class CreateGroup {

    public static void main(String [] args) {
        try{

            Credential cred = new Credential();

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-guangzhou", clientProfile);

            String params = "{\"GroupName\":\"人脸测试库\",\"GroupId\":\"du_test_01\",\"GroupExDescriptions\":[\"身份证号码\",\"运营商ID\",\"车牌号码\"],\"Tag\":\"人脸测试\"}";
            CreateGroupRequest req = CreateGroupRequest.fromJsonString(params, CreateGroupRequest.class);

            CreateGroupResponse resp = client.CreateGroup(req);

            /**
             * {"FaceModelVersion":"3.0","RequestId":"a71c8d69-3b2b-407e-809d-6835844e2c01"}
             */
            System.out.println(CreateGroupResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}
