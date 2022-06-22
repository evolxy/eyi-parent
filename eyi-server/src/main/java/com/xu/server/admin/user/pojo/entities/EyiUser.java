package com.xu.server.admin.user.pojo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xu.server.base.enums.GenderEnum;
import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/15 15:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "eyi_user")
@DynamicUpdate
@SelectBeforeUpdate
public class EyiUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3737899427754241961L;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 80, nullable = false)
    @JsonIgnore
    private String password;

    /**
     * 密码强度： 数字  大写字母 小写字母 特殊符号  包含一个为1 两个2 三个3 四个4
     */
    @Column
    private int passwordStrength;

    @Column
    private String avatar;

    @Column(length = 50)
    private String email;

    @Column
    private GenderEnum gender;

    @Column(length = 300)
    private String introduce;

    @Column
    private LocalDate birthday;

    @Column(length = 30)
    private String nickname;

    @Column
    @JsonIgnore
    private boolean expire;

    @Column
    @JsonIgnore
    private boolean locked;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "eyi_user_role",schema = "eyi",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")}
    )
    List<EyiRole> roles;
}
