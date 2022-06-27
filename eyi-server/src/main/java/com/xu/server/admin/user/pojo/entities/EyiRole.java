package com.xu.server.admin.user.pojo.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/12 14:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "eyi_role")
public class EyiRole extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 373784241961L;

	private String roleName;

	private String roleCode;

}
