package com.xu.server.storage.config;

import com.xu.commons.exception.EyiException;
import com.xu.server.storage.client.IStorageClient;
import com.xu.server.storage.client.impl.FdfsFileClient;
import com.xu.server.storage.client.impl.MinioFileClient;
import com.xu.server.storage.config.prop.FdfsProperties;
import com.xu.server.storage.config.prop.MinioClientProperties;
import com.xu.server.storage.config.prop.StorageProps;
import com.xu.server.storage.constant.StorageClient;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/8 9:41
 */
@Configuration
@EnableConfigurationProperties({StorageProps.class, MinioClientProperties.class, FdfsProperties.class})
@Slf4j
public class StorageClientConfig {
	@Value("${eyi.storage.client}")
	private StorageClient storeClient;

	private final StorageProps storageProps;

	public StorageClientConfig(StorageProps storageProps) {
		this.storageProps = storageProps;
	}

	@Bean
	public IStorageClient storageClient() {
		IStorageClient client = null;
		switch (storeClient) {
			case MINIO:
				client = new MinioFileClient();
				break;
			case FDFS:
				client = new FdfsFileClient();
				break;
			default:
				break;
		}
		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = "eyi.storage", name = "client", havingValue = "FDFS")
	public org.csource.fastdfs.StorageClient fdfsClient(@Autowired FdfsProperties properties) {
		Properties prop;
		try {
			prop = properties.covertToFdfsConfig();
			ClientGlobal.initByProperties(prop);
			log.info(" ClientGlobal.configInfo() \n {}", ClientGlobal.configInfo());
			log.debug("Storage Sever is FDFS address = {}", properties.getTrackerServer());
		} catch (EyiException | MyException | IOException e) {
			log.error(e.getMessage(), e);
		}
		log.error("FDFS 配置生效 ");
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer;
		org.csource.fastdfs.StorageClient storageClient = null;
		try {
			trackerServer = trackerClient.getTrackerServer();
			storageClient = new org.csource.fastdfs.StorageClient(trackerServer);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return storageClient;
	}

	@Bean
	@ConditionalOnProperty(prefix = "eyi.storage", name = "client", havingValue = "MINIO")
	public MinioClient minioClient(@Autowired MinioClientProperties properties) {
		return MinioClient.builder()
				.endpoint(properties.getEndpoint())
				.credentials(properties.getUsername(), properties.getPassword())
				.build();
	}
}
