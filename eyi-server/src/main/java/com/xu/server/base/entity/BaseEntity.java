package com.xu.server.base.entity;

import com.xu.commons.utils.SnowFlakeIdGenerator;
import jdk.jfr.DataAmount;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/14 15:14
 */

@Data
public class BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "snowflake")
    @GenericGenerator(name = "snowflake", strategy = "com.xu.server.base.util.SnowFlakeIdGeneratorUtil")
    private Long id;
    private byte delFlag;
    private LocalDateTime createTime;
    private LocalDateTime deleteTime;
    private LocalDateTime updateTime;
    private Long createId;
    private Long deleteId;
    private Long updateId;
}
