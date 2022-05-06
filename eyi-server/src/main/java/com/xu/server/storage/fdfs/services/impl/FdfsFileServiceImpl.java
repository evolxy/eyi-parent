package com.xu.server.storage.fdfs.services.impl;

import com.xu.server.base.service.impl.BaseServiceImpl;
import com.xu.server.storage.fdfs.pojo.entity.FdfsFile;
import com.xu.server.storage.fdfs.pojo.vo.FdfsFileVo;
import com.xu.server.storage.fdfs.properties.FdfsProperties;
import com.xu.server.storage.fdfs.repository.FdfsFileRepository;
import com.xu.server.storage.fdfs.services.IFdfsFileService;
import com.xu.server.storage.fdfs.utils.FdfsFileUtil;
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
    private final String remoteAddress ;

    public FdfsFileServiceImpl(FdfsProperties fdfsProperties) {
        remoteAddress = "http://"+FdfsFileUtil.TRACKER_HOST +":"+ fdfsProperties.getHttpTrackerHttpPort()+"/";
    }

    @Override
    public String save(String localPath) {
        String[] strings = FdfsFileUtil.uploadFile(localPath);
        if (strings == null || strings.length == 0) {
            return "";
        }
        String storePath = StringUtils.join(strings, '/');
        FdfsFile file = new FdfsFile(localPath, storePath);
        repository.save(file);
        return remoteAddress+storePath;
    }

    @Override
    public String save(File file) {
        String[] strings = FdfsFileUtil.uploadFile(file);
        if (strings == null || strings.length == 0) {
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
        return remoteAddress+storePath;
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
        String filename = file.getOriginalFilename();
        if (StringUtils.isBlank(filename)) {
            return null;
        }
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        info.setExtension(ext);
        info.setOriginName(file.getOriginalFilename());
        repository.save(info);
        return remoteAddress+storePath;
    }

    @Override
    public FdfsFileVo downloadFile(Long id) {
        FdfsFile fdfsFile = getById(id);
        if (fdfsFile==null) {
            return null;
        }
        String storePath = fdfsFile.getStorePath();
        byte[] bytes = FdfsFileUtil.downloadFile(storePath);
        FdfsFileVo fileVo = new FdfsFileVo();
        fileVo.setContent(bytes);
        fileVo.setExt(fdfsFile.getExtension());
        fileVo.setFilename(fdfsFile.getOriginName());
        return fileVo;
    }

    @Override
    public boolean downloadFile(Long id, String path) {
        FdfsFile fdfsFile = getById(id);
        if (fdfsFile==null) {
            return false;
        }
        String storePath = fdfsFile.getStorePath();
        return FdfsFileUtil.downloadFile(storePath, path);
    }

    @Override
    public boolean removeById(Long id) {
        FdfsFile file = repository.findById(id).orElse(null);
        if (file == null) {
            return false;
        }
        String storePath = file.getStorePath();
        boolean deleteFile = FdfsFileUtil.deleteFile(storePath);
        repository.logicDelById(id);
        return deleteFile;
    }
}
