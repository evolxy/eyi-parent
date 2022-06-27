package com.xu.server.admin.article.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章标签
 * @author Author
 * @version 0.1
 * Created On 2022/4/1 16:10
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "eyi_catalog")
@Data
public class Catalog extends BaseEntity {
    private static final long serialVersionUID = 3737899427754241L;

    @TableField
    private String catalogName;

    @TableField
    private String catalogCode;
}
