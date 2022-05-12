package com.xu.server.admin.user.pojo.entities;

import com.xu.commons.utils.SnowFlakeIdGenerator;
import com.xu.server.base.pojo.entity.BaseEntity;
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
@Table(name = "eyi_user_role")
public class EyiUserRole extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 27754241961L;

	public EyiUserRole() {
		setId(SnowFlakeIdGenerator.getFlowIdInstance().nextId());
	}
}
