package com.xu.server.storage.fdfs.utils;

import com.xu.server.base.util.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/18 14:28
 */
@Slf4j
public class FdfsFileUtil {
    private static StorageClient storageClient;
    public static final String TRACKER_HOST;
    static {
        initStorageClient();
        TRACKER_HOST = storageClient.getTrackerServer().getInetSocketAddress().getHostName();
    }
    private static final String PREFIX = "/";
    /**  初始化  */
    public static void initStorageClient() {
        if (storageClient==null) {
            FdfsFileUtil.storageClient = ApplicationContextUtil.getBean(StorageClient.class);
        }
    }

    /** 上传  */
    public static String[] uploadFile(String localPath) {
        return uploadFile(new File(localPath));
    }

    public static String[] uploadFile(String localPath, NameValuePair[] metaInfo) {
        String ext = localPath.substring(localPath.lastIndexOf(".") + 1);
        return uploadFile(localPath, ext, metaInfo);
    }

    public static String[] uploadFile(String localPath, String fileExt, NameValuePair[] metaInfo) {
        File file = new File(localPath);
        if (!file.exists()) {
            log.error("请确定文件是否有效");
            return null;
        }
        String[] uploadFile = null;
        try {
            uploadFile = storageClient.upload_file(localPath, fileExt, metaInfo);
        } catch (IOException | MyException e) {
            log.error(e.getMessage(), e);
        }
        return uploadFile;
    }

    public static String[] uploadFile(File file) {
        if (!file.exists()) {
            log.error("文件不存在!");
            return null;
        }
        return uploadFile(file, null);
    }

    public static String[] uploadFile(File file, NameValuePair[] pairs) {
        String[] res = null;
        try (InputStream fis = new FileInputStream(file)){
            byte[] bytes = fis.readAllBytes();
            String path = file.getPath();
            int lastIdx = path.lastIndexOf(".") + 1;
            String extension = path.substring(lastIdx);
            res = uploadFile(bytes, extension, pairs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String[] uploadFile(MultipartFile file) {
        String[] res = null;
        try {
            String originalFilename = file.getOriginalFilename();
            if (StringUtils.isBlank(originalFilename)) {
                return null;
            }
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            byte[] bytes = file.getBytes();
            res = uploadFile(bytes, ext, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String[] uploadFile(byte[] file, String fileExt, NameValuePair[] metaInfo) {
        String[] uploadFile = null;
        try {
            uploadFile = storageClient.upload_file(file, fileExt, metaInfo);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return uploadFile;
    }

    /* 上传 end */

    /**  下载 */
    public static byte[] downloadFile(String storePath) {
        
        if (storePath.startsWith(PREFIX)) {
            storePath = storePath.substring(1);
        }
        int groupIdx = storePath.indexOf(PREFIX);
        String groupName = storePath.substring(0, groupIdx);
        String remotePath = storePath.substring(groupIdx + 1);
        byte[] bytes = null;
        try {
            bytes = storageClient.download_file(groupName, remotePath);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static boolean downloadFile(String storePath, String localPath) {
        byte[] bytes = downloadFile(storePath);
        try (FileOutputStream writer = new FileOutputStream(localPath)){
            writer.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(localPath).exists();
    }
    /* 下载 end */

    /**  删除 */
    public static boolean deleteFile(String storePath) {
        
        if (storePath.startsWith(PREFIX)) {
            storePath = storePath.substring(1);
        }
        int groupIdx = storePath.indexOf(PREFIX);
        String groupName = storePath.substring(0, groupIdx);
        String remotePath = storePath.substring(groupIdx + 1);
        int deleteFile = 0;
        try {
            deleteFile = storageClient.delete_file(groupName, remotePath);
            log.error("delete file res = {}", deleteFile);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return deleteFile == 0;
    }
    /* 删除 end */
}
