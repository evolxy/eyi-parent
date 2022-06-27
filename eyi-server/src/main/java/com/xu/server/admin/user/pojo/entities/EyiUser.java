package com.xu.server.admin.user.pojo.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.xu.server.base.enums.GenderEnum;
import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/15 15:31
 */
@EqualsAndHashCode(callSuper = true)
@Data

@TableName(value = "eyi_user")
public class EyiUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3737899427754241961L;

    @TableField
    private String username;

    @TableField
    @JsonIgnore
    private String password;

    /**
     * 密码强度： 数字  大写字母 小写字母 特殊符号  包含一个为1 两个2 三个3 四个4
     */
    @TableField
    private Integer passwordStrength;

    @TableField
    private String avatar;

    @TableField
    private String email;

    @TableField
    private GenderEnum gender;

    @TableField
    private String introduce;

    @TableField
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthday;

    @TableField
    private String nickname;

    @TableField
    @JsonIgnore
    private Boolean expire;

    @TableField
    @JsonIgnore
    private Boolean locked;
}
