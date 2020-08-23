package com.du.facedemo.person;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.GetPersonBaseInfoRequest;
import com.tencentcloudapi.iai.v20200303.models.GetPersonBaseInfoResponse;

/**
 * @author Du
 * @date 2020/8/22 20:35
 */
public class GetPersonBaseInfo {

    public static void main(String [] args) {
        try{

            Credential cred = new Credential("AKID2uKXUBG3GCrFNDLskMltkvqUta1Ajc2D", "UifNp1g4HSQ66rJXUCn8CQO1NSI4aoCL");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-guangzhou", clientProfile);

            String params = "{\"PersonId\":\"du_01\"}";
            GetPersonBaseInfoRequest req = GetPersonBaseInfoRequest.fromJsonString(params, GetPersonBaseInfoRequest.class);

            GetPersonBaseInfoResponse resp = client.GetPersonBaseInfo(req);

            //{"PersonName":"du","Gender":1,"FaceIds":["3783102146669485878"],"RequestId":"d50a60da-996d-4e32-baf3-98274d9e670c"}
            System.out.println(GetPersonBaseInfoResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }
}
