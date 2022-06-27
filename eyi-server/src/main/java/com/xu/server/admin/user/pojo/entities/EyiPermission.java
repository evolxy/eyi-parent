package com.xu.server.admin.user.pojo.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/12 14:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "eyi_permission")
public class EyiPermission extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 39427754241961L;

	@TableField
	private String permissionName;

	@TableField
	private String permissionCode;
}
