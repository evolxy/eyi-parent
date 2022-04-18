package com.xu.server.storage.services.impl;

import com.xu.server.base.service.impl.BaseServiceImpl;
import com.xu.server.storage.pojo.entity.FdfsFile;
import com.xu.server.storage.repository.FdfsFileRepository;
import com.xu.server.storage.services.IFdfsFileService;
import com.xu.server.storage.utils.FdfsFileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/18 15:48
 */
@Service
public class FdfsFileServiceImpl extends BaseServiceImpl<FdfsFile, FdfsFileRepository> implements IFdfsFileService {

    @Override
    public String save(String localPath) {
        String[] strings = FdfsFileUtil.uploadFile(localPath);
        if (strings==null || strings.length==0) {
            return "";
        }
        String storePath = StringUtils.join(strings, '/');
        FdfsFile file = new FdfsFile(localPath, storePath);
        repository.save(file);
        return storePath;
    }

    @Override
    public String save(File file) {
        String[] strings = FdfsFileUtil.uploadFile(file);
        if (strings==null || strings.length==0) {
            return "";
        }
        String storePath = StringUtils.join(strings, '/');
        FdfsFile info = new FdfsFile();
        info.setStorePath(storePath);
        String fileName = file.getName();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        info.setExtension(ext);
        info.setOriginName(file.getName());
        repository.save(info);
        return storePath;
    }

    @Override
    public String save(MultipartFile file) {
        String[] strings = FdfsFileUtil.uploadFile(file);
        if (strings==null || strings.length==0) {
            return "";
        }
        String storePath = StringUtils.join(strings, '/');
        FdfsFile info = new FdfsFile();
        info.setStorePath(storePath);
        String filename = file.getResource().getFilename();
        if (StringUtils.isBlank(filename)) {
            return null;
        }
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        info.setExtension(ext);
        info.setOriginName(file.getName());
        repository.save(info);
        return storePath;
    }
}
