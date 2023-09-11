package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/admin/common")
public class CommonController {


    @Autowired
    private AliOssUtil aliOssUtil;

    @Operation(summary = "文件上传")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
        String url = null;
        try {
            url = aliOssUtil.upload(file.getBytes(),newName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Result.success(url);
    }
}
