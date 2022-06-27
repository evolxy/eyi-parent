package com.xu.server.base.handlers;

import com.xu.commons.exception.EyiException;
import com.xu.server.base.enums.BaseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/27 17:00
 */

@Slf4j
public class EnumAutoConvertHandler<E extends BaseEnum> extends BaseTypeHandler<E> {
	private Class<E> type;
	private E[] enums;


	public EnumAutoConvertHandler(Class<E> type) throws EyiException {
		if (type == null) {
			throw new EyiException("type 不能为null");
		}
		this.type = type;
		this.enums = type.getEnumConstants();
		if (enums == null || enums.length < 1) {
			throw new EyiException(type.getSimpleName() + " 常量为空");
		}
	}

	private E loadEnum(Object index) throws EyiException {
		E e = Arrays.stream(enums).filter(it -> it.getValue().toString().equals(index.toString())).findFirst().orElse(null);
		if (e == null) {
			throw new EyiException("index " + index + " 无效");
		}
		return e;
	}

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
		preparedStatement.setObject(i, e.getValue());
	}

	@Override
	public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
		Object index = resultSet.getObject(s);
		if (index == null) {
			return null;
		}
		try {
			return loadEnum(index);
		} catch (EyiException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
		Object index = resultSet.getObject(i);
		if (index == null) {
			return null;
		}
		try {
			return loadEnum(index);
		} catch (EyiException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		Object index = callableStatement.getObject(i);
		if (index == null) {
			return null;
		}
		try {
			return loadEnum(index);
		} catch (EyiException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}
