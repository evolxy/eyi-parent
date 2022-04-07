package com.xu.server.admin.article.pojo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/1 14:02
 */
@AllArgsConstructor
@Getter
@Setter
@Document("article")
public class ArticleDoc implements Serializable {
    private static final long serialVersionUID = 37378994277542961L;

    public ArticleDoc() {
        LocalDateTime now = LocalDateTime.now();
        this.createTime = now;
        this.updateTime = now;
        this.delFlag = false;
        this.locked = false;
    }

    private ObjectId id;

    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime deleteTime;

    private boolean delFlag;

    private boolean locked;
}
