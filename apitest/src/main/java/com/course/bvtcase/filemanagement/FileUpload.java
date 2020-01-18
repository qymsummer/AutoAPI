package com.course.bvtcase.filemanagement;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;

import com.course.utils.ReadFile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;

import org.springframework.web.multipart.MultipartFile;
/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/13 14:49
 * @author qym
 */
public class FileUpload {
    @BeforeMethod(
            description = "文件上传"
    )

    public void beforeMethod() {
        TestConfig.addFileInfo = ConfigFile.getUrl(InterfaceName.ADDFILEINFO);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "FileUpload",description = "文件上传")
    public void fileUpload() throws IOException, InterruptedException {



        Thread.sleep(100);
        HttpPost httpPost = new HttpPost(TestConfig.addFileInfo);
        /*
        InputStream ss = ReadFile.readFile("E:\\RuanJian\\ID.txt");
        File file = new File("E:\\RuanJian\\ID.txt");
        System.out.println(file);
        MultipartFile multipartFile = new MockMultipartFile(ss);
        //System.out.println(ss);
*/
        /*

        File file = new File("E:\\RuanJian\\ID.txt");
        FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

        try {
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
            // Or faster..
            // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
        } catch (IOException ex) {
            // do something.
        }

        MultipartFile multipartFile = new MockMultipartFile(file);

         */
        File file = new File("E:\\RuanJian\\ID.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        System.out.println(multipartFile);
        JSONObject params = new JSONObject();
        params.put("fileName","11");
        params.put("fileDesc","11");
        params.put("fileType","0");
        params.put("platformId","6");
        params.put("id","290");
        params.put("name","file");

        params.put("filename","ID.txt");


//      httpPost.setHeader("content-type", "multipart/form-data");
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        httpPost.setEntity(entity);
        HttpResponse response =  TestConfig.client.execute (httpPost);
        String result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println("测试结果:"+"\n"+result);

    }
}

