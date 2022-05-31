package com.xu.server.base.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/31 16:51
 */

public class RedisUtils {
	private static RedisTemplate<String, Object> template;

	/**
	 * 默认过期时间 60 * 60 * 24
	 */
	public final static long DEFAULT_EXPIRE = 86400;

	/**  不设置过期时长 */
	public final static long NOT_EXPIRE = -1;

	public static void setTemplate(RedisTemplate<String, Object> template) {
		RedisUtils.template = template;
	}

	public static void set(String key, Object value, long expire) {
		if (expire > 0) {
			template.opsForValue().set(key, value, expire, TimeUnit.MINUTES);
		} else {
			template.opsForValue().set(key, value);
		}
	}

	public static String toJsonString(Object object) {
		return JSONObject.toJSONString(object);
	}
}
