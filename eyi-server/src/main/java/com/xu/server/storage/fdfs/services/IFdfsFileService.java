package com.xu.server.storage.fdfs.services;

import com.xu.server.base.service.IBaseService;
import com.xu.server.storage.fdfs.pojo.entity.FdfsFile;
import com.xu.server.storage.fdfs.pojo.vo.FdfsFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/18 15:46
 */

public interface IFdfsFileService extends IBaseService<FdfsFile> {
    String save(String localPath);

    String save(File file);

    String save(MultipartFile file);

    boolean removeById(Long id);

    FdfsFileVo downloadFile(Long id);

    boolean downloadFile(Long id, String path);
}
