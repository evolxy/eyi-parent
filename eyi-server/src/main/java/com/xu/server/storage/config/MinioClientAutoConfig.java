package com.xu.server.storage.config;

import com.xu.server.storage.config.prop.MinioClientProperties;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/31 10:54
 */

@Configuration
@EnableConfigurationProperties(MinioClientProperties.class)
@Slf4j
@ConditionalOnProperty(prefix = "eyi.storage", name = "client", havingValue = "MINIO")
public class MinioClientAutoConfig {
	private final MinioClientProperties properties;

	public MinioClientAutoConfig(MinioClientProperties properties) {
		this.properties = properties;
	}

	@Bean
	public MinioClient minioClient() {
		log.error("Minio client 有效");
		return MinioClient.builder()
				.endpoint(properties.getEndpoint())
				.credentials(properties.getUsername(), properties.getPassword())
				.build();
	}

}
