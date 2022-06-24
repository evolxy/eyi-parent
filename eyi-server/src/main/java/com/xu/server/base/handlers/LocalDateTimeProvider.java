package com.xu.server.base.handlers;

import org.springframework.data.auditing.DateTimeProvider;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/23 9:34
 */

public class LocalDateTimeProvider implements DateTimeProvider {
	@Override
	public Optional<TemporalAccessor> getNow() {
		return Optional.of(LocalDateTime.now());
	}
}
