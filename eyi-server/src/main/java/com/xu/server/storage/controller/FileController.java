package com.xu.server.storage.controller;

import com.xu.commons.result.Result;
import com.xu.server.storage.services.IFdfsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/18 16:26
 */
@RestController
@RequestMapping("/eyi/fdfs")
public class FileController {
    @Autowired
    private IFdfsFileService fdfsFileService;

    @PostMapping("")
    public Result<?> save(MultipartFile file) {
        String path = fdfsFileService.save(file);
        return Result.ok().data(path);
    }
}
