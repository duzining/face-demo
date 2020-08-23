package com.du.facedemo.person;


import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.CreatePersonRequest;
import com.tencentcloudapi.iai.v20200303.models.CreatePersonResponse;

/**
 * @author Du
 * @date 2020/8/22 19:32
 */
public class CreatePerson {


    public static void main(String [] args) {
        try{

            Credential cred = new Credential();

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-guangzhou", clientProfile);

            String params = "{\"GroupId\":\"du_test_01\",\"PersonName\":\"du\",\"PersonId\":\"du_01\",\"Gender\":1,\"PersonExDescriptionInfos\":[{\"PersonExDescriptionIndex\":0,\"PersonExDescription\":\"440304199910204120\"},{\"PersonExDescriptionIndex\":1,\"PersonExDescription\":\"4\"},{\"PersonExDescriptionIndex\":2,\"PersonExDescription\":\"粤66666\"}]}";
            CreatePersonRequest req = CreatePersonRequest.fromJsonString(params, CreatePersonRequest.class);

            CreatePersonResponse resp = client.CreatePerson(req);

            System.out.println(CreatePersonResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }
}
