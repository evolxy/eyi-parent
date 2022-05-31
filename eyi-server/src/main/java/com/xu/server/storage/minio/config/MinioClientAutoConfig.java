package com.xu.server.storage.minio.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MinioClientAutoConfig {
	private final MinioClientProperties properties;

	public MinioClientAutoConfig(MinioClientProperties properties) {
		this.properties = properties;
	}

	@Bean
	public MinioClient minioClient() {
		return MinioClient.builder()
				.endpoint(properties.getEndpoint())
				.credentials(properties.getUsername(), properties.getPassword())
				.build();
	}

}
