package com.xu.server.storage.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/18 15:41
 */

@EqualsAndHashCode(callSuper = true)
@TableName(value = "eyi_file_info")
@Data
@NoArgsConstructor
public class FileInfo extends BaseEntity {
    private static final long serialVersionUID = 373789942775421L;

    private String originName;

    private String storePath;

    private String extension;

    public FileInfo(String localPath, String storePath) {
        int originNameIdx = localPath.lastIndexOf(File.separator);
        int extensionIdx = localPath.lastIndexOf(".");

        String originName = localPath.substring(originNameIdx + 1, extensionIdx);
        this.extension = localPath.substring(extensionIdx + 1);
        this.originName = originName;
        this.storePath = storePath;
    }
}
