package com.xu.server.admin.user.pojo.entities;

import com.xu.commons.utils.SnowFlakeIdGenerator;
import com.xu.server.base.pojo.entity.BaseEntity;
import com.xu.server.base.util.SnowFlakeIdGeneratorUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/12 14:27
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "eyi_role_permission")
public class EyiRolePermission extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 373789942775L;

	private Long roleId;

	private Long permissionId;

	public EyiRolePermission() {
		setId(SnowFlakeIdGenerator.getFlowIdInstance().nextId());
	}
}


