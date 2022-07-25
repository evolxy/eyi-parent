package com.xu.server.base.handlers;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xu.server.base.enums.DelFlagEnum;
import com.xu.server.base.util.LoginUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/27 15:18
 */
@Component
@Slf4j
public class TableFieldAutoFillHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		// 起始版本 3.3.3(推荐)
		this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
		this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
		this.strictInsertFill(metaObject, "delFlag", DelFlagEnum.NOT_DELETED::getValue, Integer.class);
		boolean login = StpUtil.isLogin();
		if (login) {
			this.strictInsertFill(metaObject, "createId", LoginUserUtil::loginUserId, Long.class);
			this.strictInsertFill(metaObject, "updateId", LoginUserUtil::loginUserId, Long.class);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		// 起始版本 3.3.3(推荐)
		this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
		boolean login = StpUtil.isLogin();
		if (login) {
			this.strictInsertFill(metaObject, "updateId", LoginUserUtil::loginUserId, Long.class);
		}
	}
}
