package com.winter.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.winter.bean.ImgInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value="snail/")
public class UploadController {

    @Value(value="${upload.fileRootPath}")
    private String fileRootPath;

    /**
     * 富文本中添加图片
     * @param file
     * @param response
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("upload/editor")
    @ResponseBody
    public ImgInfo setImgUrl(MultipartFile file, HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        // Get the file and save it somewhere
        byte[] bytes = file.getBytes();
//        System.out.println(new String(bytes));
        //使用这种方式会因为重启服务而获取不到图片
//        String path = request.getServletContext().getRealPath("/image/");
        /**
         * 创建文件夹：D:/upload/snail/年月日时/
         */
        String fileUrl = fileRootPath;
        Date dateTime = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHH");
        String currentDate = sdf1.format(dateTime);
        fileUrl = fileUrl + currentDate;

        File imgFile = new File(fileUrl);
        if (!imgFile.exists()) {
            imgFile.mkdirs();
        }

        /**
         * 创建文件
         */
        String fileName = file.getOriginalFilename();// 文件名称
        String filePath = fileUrl + "\\" +fileName;
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            int len = 0;
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        String value = "http://localhost:8088/snail/"+currentDate+"/"+fileName;
        String value = "/snail/"+currentDate+"/"+fileName;
        String[] values = { value };

        ImgInfo imgInfo = new ImgInfo();
        imgInfo.setError(0);
        imgInfo.setUrl(values);

//        System.out.println(imgInfo.toString());
        return imgInfo;
    }



}
