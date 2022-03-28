package com.xu.server.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Transient
    private static final long serialVersionUID = 3737899427754241961L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "snowflake")
    @GenericGenerator(name = "snowflake", strategy = "com.xu.server.base.util.SnowFlakeIdGeneratorUtil")
    private Long id;

    @Column
    @JsonIgnore
    private byte delFlag;

    @Column
    private LocalDateTime createTime;

    @Column
    private LocalDateTime deleteTime;

    @Column
    private LocalDateTime updateTime;

    @Column
    private Long createId;

    @Column
    private Long deleteId;

    @Column
    private Long updateId;
}
