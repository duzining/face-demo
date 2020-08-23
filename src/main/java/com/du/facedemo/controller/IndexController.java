package com.du.facedemo.controller;

import com.du.facedemo.service.CreatePersonService;
import com.du.facedemo.service.QiniuService;
import com.du.facedemo.service.VerifyFaceService;
import com.du.facedemo.utill.Base64ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Du
 * @date 2020/8/22 14:45
 */
@Controller
public class IndexController {

    @Autowired
    private QiniuService qiniuService;

    @Autowired
    private CreatePersonService createPersonService;

    @Autowired
    private VerifyFaceService verifyFaceService;

    @RequestMapping("/test")
    public String index(){
        return "test";
    }

    @RequestMapping(value = "/testUpload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        if(file.isEmpty()) {
            return "error";
        }

        try {
            String imgBase64 = Base64ConvertUtils.getImageStr(file);
            String fileUrl=qiniuService.saveImage(file);
            createPersonService.createPerson(imgBase64,fileUrl);
            return "success, imageUrl = " + fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseBody
    public String verify(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        if(file.isEmpty()) {
            return "error";
        }

        try {
            String imgBase64 = Base64ConvertUtils.getImageStr(file);
            String fileUrl=qiniuService.saveImage(file);
            verifyFaceService.verifyFace(imgBase64,fileUrl);
            return "success, imageUrl = " + fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
