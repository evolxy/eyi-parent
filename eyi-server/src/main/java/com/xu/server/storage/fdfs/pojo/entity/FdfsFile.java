package com.xu.server.storage.fdfs.pojo.entity;

import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.File;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/18 15:41
 */

@EqualsAndHashCode(callSuper = true)
@Table(name = "eyi_file_info")
@Entity
@Data
@NoArgsConstructor
public class FdfsFile extends BaseEntity {
    private static final long serialVersionUID = 373789942775421L;

    private String originName;

    private String storePath;

    private String extension;

    public FdfsFile(String localPath, String storePath) {
        int originNameIdx = localPath.lastIndexOf(File.separator);
        int extensionIdx = localPath.lastIndexOf(".");

        String originName = localPath.substring(originNameIdx + 1, extensionIdx);
        this.extension = localPath.substring(extensionIdx + 1);
        this.originName = originName;
        this.storePath = storePath;
    }
}
