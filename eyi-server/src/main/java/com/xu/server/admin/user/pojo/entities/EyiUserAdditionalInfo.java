package com.xu.server.admin.user.pojo.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xu.server.base.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/28 10:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("eyi_user_additional_info")
public class EyiUserAdditionalInfo extends BaseEntity {
	/**
	 * 密码强度： 数字  大写字母 小写字母 特殊符号  包含一个为1 两个2 三个3 四个4
	 */
	@TableField
	private Integer passwordStrength;

	@TableField
	private String emailAddr;

	@TableField
	private String phone;

	@TableField
	private String question;

	@TableField
	private String answer;

	@TableField
	private Integer status;
}
