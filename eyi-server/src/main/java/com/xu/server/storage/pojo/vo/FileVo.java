package com.xu.server.storage.pojo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/19 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class FileVo {
    private byte[] content;

    private String filename;

    private String ext;
}
