package com.xu.server.admin.user.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xu.server.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/15 15:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "eyi_user")
public class EyiUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3737899427754241961L;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 80, nullable = false)
    private String password;

    @Column(length = 50)
    private String email;

    @Column
    private boolean gender;

    @Column(length = 300)
    private String introduce;

    @Column
    @JsonIgnore
    private boolean expire;

    @Column
    @JsonIgnore
    private boolean locked;
}
