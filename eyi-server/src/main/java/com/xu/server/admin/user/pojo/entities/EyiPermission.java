package com.xu.server.admin.user.pojo.entities;

import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/12 14:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "eyi_permission")
public class EyiPermission extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 39427754241961L;

	private String permissionName;

	private String permissionCode;
}
