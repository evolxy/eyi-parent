package com.xu.server.base.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**常用方法见 https://wenku.baidu.com/view/e141701c84c24028915f804d2b160b4e767f81ba.html
 * @author Author
 * @version 0.1
 * Created On 2022/5/31 16:51
 */

public class RedisUtils {
	private static RedisTemplate<String, Object> template;

	/**
	 * 默认过期时间 60 * 60 * 24
	 */
	private final static long DEFAULT_EXPIRE = 86400;

	/**  不设置过期时长 */
	private final static long NOT_EXPIRE = -1;

	public static void setTemplate(RedisTemplate<String, Object> template) {
		RedisUtils.template = template;
	}

	/**
	 * 自定过期时间
	 * @param key key
	 * @param value v
	 * @param expire 过期时间 s
	 */
	public static void set(String key, Object value, long expire) {
		if (expire > 0) {
			template.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
		} else {
			template.opsForValue().set(key, value);
		}
	}

	/**
	 * 不过期
	 * @param key key
	 * @param value v
	 */
	public static void set(String key, Object value) {
		set(key, value, -1);
	}

	/**
	 * 默认过期时长 1天
	 * @param key k
	 * @param value v
	 */
	public static void setExpiredValue(String key, Object value) {
		set(key, value, DEFAULT_EXPIRE);
	}

	/**
	 * 设置过期时间
	 * @param key k
	 * @param expire 过期时间 s
	 * @return true|false
	 */
	public static boolean expire(String key, long expire) {
		if (hasKey(key)) {
			template.expire(key, expire, TimeUnit.SECONDS);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否存在key
	 * @param key k
	 * @return true|false
	 */
	public static boolean hasKey(String key) {
		return Boolean.TRUE.equals(template.hasKey(key));
	}

	/**
	 * 获取value
	 * @param key key
	 * @return value
	 */
	public static Object get(String key) {
		Object obj = null;
		if (hasKey(key)) {
			obj = template.opsForValue().get(key);
		}
		return obj;
	}

	/**
	 * 根据k删除
	 * @param key k
	 */
	public static void delete(String key) {
		if (!hasKey(key)) {
			return;
		}
		template.delete(key);
	}
}
