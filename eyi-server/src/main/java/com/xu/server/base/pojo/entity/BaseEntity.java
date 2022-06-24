package com.xu.server.base.pojo.entity;

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
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/14 15:14
 */

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class BaseEntity implements Serializable {
    public BaseEntity() {
        this.delFlag = DelFlagEnum.NOT_DELETED.getValue();
    }

    @Transient
    private static final long serialVersionUID = 3737899427754241961L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "snowflake")
    @GenericGenerator(name = "snowflake", strategy = "com.xu.server.base.util.SnowFlakeIdGeneratorUtil")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Column
    @JsonIgnore
    private Integer delFlag;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @CreatedDate
    private LocalDateTime createTime;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime deleteTime;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @LastModifiedDate
    private LocalDateTime updateTime;

    @Column
    @CreatedBy
    private Long createId;

    @Column
    private Long deleteId;

    @Column
    @LastModifiedBy
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
