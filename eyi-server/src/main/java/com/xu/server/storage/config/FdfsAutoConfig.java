package com.xu.server.storage.config;

import com.xu.commons.exception.EyiException;
import com.xu.server.storage.properties.FdfsProperties;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/18 9:43
 */

@EnableConfigurationProperties(FdfsProperties.class)
@Configuration
@Slf4j
public class FdfsAutoConfig {

    public FdfsAutoConfig(FdfsProperties properties) {
        Properties prop;
        try {
            prop = properties.covertToFdfsConfig();
            ClientGlobal.initByProperties(prop);
            log.info(" ClientGlobal.configInfo() \n {}", ClientGlobal.configInfo());
        } catch (EyiException | MyException | IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public StorageClient storageClient() {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer;
        StorageClient storageClient = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
            storageClient = new StorageClient(trackerServer);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return storageClient;
    }
}
