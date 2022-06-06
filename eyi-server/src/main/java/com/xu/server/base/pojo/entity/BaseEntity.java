package com.xu.server.base.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

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
public class BaseEntity implements Serializable {
    public BaseEntity() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        this.delFlag = (byte) 0;
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
    private byte delFlag;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
    private LocalDateTime updateTime;

    @Column
    private Long createId;

    @Column
    private Long deleteId;

    @Column
    private Long updateId;
}
