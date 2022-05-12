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
    /**
     * 保存本地文件
     * @param localPath 本地文件位置
     * @return 远程访问地址
     */
    String save(String localPath);

    /**
     * 保存本地文件
     * @param file 本地文件位置
     * @return 远程访问地址
     */
    String save(File file);

    /**
     * 保存文件
     * @param file file
     * @return 远程访问地址
     */
    String save(MultipartFile file);

    /**
     * 根据id删除文件信息 及文件
     * @param id id
     * @return true|false
     */
    @Override
    boolean removeById(Long id);

    /**
     * 下载文件
     * @param id id
     * @return true|false
     */
    FdfsFileVo downloadFile(Long id);

    /**
     * 下载文件到本地
     * @param id id
     * @param path 本地地址
     * @return ture|false
     */
    boolean downloadFile(Long id, String path);
}
