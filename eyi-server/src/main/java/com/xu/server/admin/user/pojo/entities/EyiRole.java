package com.xu.server.admin.user.pojo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/12 14:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "eyi_role")
public class EyiRole extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 373784241961L;

	private String roleName;

	private String roleCode;

	@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinTable(name = "eyi_role_permission",schema = "eyi",
			joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "permissionId", referencedColumnName = "id")}
	)
	List<EyiPermission> permissions;
}
