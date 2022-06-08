package com.xu.server.storage.config.prop;

import com.xu.server.storage.constant.StorageClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/8 10:23
 */
@Data
@ConfigurationProperties(prefix = "eyi.storage")
@Slf4j
public class StorageProps {
	private StorageClient client;
}
