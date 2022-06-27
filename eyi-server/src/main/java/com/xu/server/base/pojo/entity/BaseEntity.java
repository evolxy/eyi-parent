package com.xu.server.base.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.xu.server.base.enums.DelFlagEnum;
import com.xu.server.base.util.EyiLoginUserUtil;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/14 15:14
 */

@Data
public class BaseEntity implements Serializable {
    public BaseEntity() {
        this.delFlag = DelFlagEnum.NOT_DELETED.getValue();
    }

    @TableField(exist = false)
    private static final long serialVersionUID = 3737899427754241961L;

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @TableLogic(value = "0", delval = "1")
    @JsonIgnore
    private Integer delFlag;

    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime deleteTime;

    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    private Long createId;

    
    private Long deleteId;

    private Long updateId;

    public void deleteBaseProps() {
        this.setDelFlag(null);
        this.setCreateTime(null);
        this.setUpdateTime(null);
    }

    public void setUpdateInfo() {
        this.updateTime = LocalDateTime.now();
        this.updateId = EyiLoginUserUtil.loginUserId();
    }

    public void setInsertInfo() {
        this.delFlag = DelFlagEnum.NOT_DELETED.getValue();
        setUpdateInfo();
        this.createId = EyiLoginUserUtil.loginUserId();
        this.createTime = LocalDateTime.now();
    }
}
