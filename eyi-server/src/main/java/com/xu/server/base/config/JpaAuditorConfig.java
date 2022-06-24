package com.xu.server.base.config;

import com.xu.server.base.handlers.CreateOrUpdateByAuditorAware;
import com.xu.server.base.handlers.LocalDateTimeProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/23 9:39
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditorConfig {
	/**
	 * 自动添加更新时间和创建时间
	 * @return customer
	 */
	@Bean
	public LocalDateTimeProvider dateTimeProvider() {
		return new LocalDateTimeProvider();
	}

	@Bean
	public CreateOrUpdateByAuditorAware auditorAware() {
		return new CreateOrUpdateByAuditorAware();
	}
}
