package com.xu.server.storage.fdfs.properties;

import com.xu.commons.exception.EyiException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Properties;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/18 9:44
 */

@ConfigurationProperties(prefix = "eyi.storage.fdfs")
@Data
@Slf4j
public class FdfsProperties {
    /**
     * timeunit second
     */
    private int connectTimeOut;

    /**
     * timeunit second
     */
    private int networkTimeOut;

    private String charset;

    private String httpTrackerHttpPort ;

    private List<String> trackerServer;

    private boolean httpAntiStealToken ;

    private String httpSecretKey;

    private ConnectionPool connectionPool;

    private final static String FASTDFS_PREFIX="fastdfs.";

    @Data
    static class ConnectionPool {
        private boolean poolEnabled;

        private int maxCountPerEntry;

        private int maxIdleTime;

        private int maxWaitTimeInMs;
    }

    public Properties covertToFdfsConfig() throws EyiException {
        if(CollectionUtils.isEmpty(this.trackerServer)) {
            log.error("请正确配置fastDFS tracker_server");
            throw new EyiException("请正确配置fastDFS tracker_server");
        }
        Properties properties = new Properties();
        properties.setProperty(FASTDFS_PREFIX+"tracker_servers", Strings.join(this.getTrackerServer(), ','));
        if (StringUtils.isNotBlank(charset)) {
            properties.setProperty(FASTDFS_PREFIX+"charset", charset);
        }
        if (StringUtils.isNotBlank(httpTrackerHttpPort)) {
            properties.setProperty(FASTDFS_PREFIX+"http_tracker_http_port", httpTrackerHttpPort);
        }
        if (connectTimeOut > 0) {
            properties.setProperty(FASTDFS_PREFIX+"connect_timeout_in_seconds", String.valueOf(connectTimeOut));
        }
        if (networkTimeOut > 0) {
            properties.setProperty(FASTDFS_PREFIX+"network_timeout_in_seconds", String.valueOf(networkTimeOut));
        }

        properties.setProperty(FASTDFS_PREFIX+"http_anti_steal_token", String.valueOf(false));
        if (StringUtils.isNotBlank(httpSecretKey)) {
            properties.setProperty(FASTDFS_PREFIX+"http_secret_key", httpSecretKey);
        }
        if (connectionPool!=null) {
            if (!connectionPool.poolEnabled) {
                properties.setProperty(FASTDFS_PREFIX+"connection_pool.enabled", String.valueOf(false));
            }
            if (connectionPool.maxCountPerEntry>0) {
                properties.setProperty(FASTDFS_PREFIX+"connection_pool.max_count_per_entry", String.valueOf(connectionPool.maxCountPerEntry));
            }
            if (connectionPool.maxIdleTime>0) {
                properties.setProperty(FASTDFS_PREFIX+"connection_pool.max_idle_time", String.valueOf(connectionPool.maxIdleTime));
            }
            if (connectionPool.maxWaitTimeInMs>0) {
                properties.setProperty(FASTDFS_PREFIX+"connection_pool.max_wait_time_in_ms", String.valueOf(connectionPool.maxWaitTimeInMs));
            }
        }
        return properties;
    }
}
