package com.xu.server.storage.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/31 10:55
 */

@Data
@ConfigurationProperties(prefix = "eyi.storage.minio")
public class MinioClientProperties {
	private String endpoint;

	private String username;

	private String password;
}
