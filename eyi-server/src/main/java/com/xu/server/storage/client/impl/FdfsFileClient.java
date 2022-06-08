package com.xu.server.storage.client.impl;

import com.xu.server.base.util.ApplicationContextUtil;
import com.xu.server.storage.client.IStorageClient;
import com.xu.server.storage.config.prop.FdfsProperties;
import com.xu.server.storage.utils.FdfsFileUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/7 17:03
 */

public class FdfsFileClient implements IStorageClient {

	private static final String BASE_URL;

	static {
		FdfsProperties fdfsProperties = ApplicationContextUtil.getBean(FdfsProperties.class);
		BASE_URL = "http://" + FdfsFileUtil.TRACKER_HOST + ":" + fdfsProperties.getHttpTrackerHttpPort() + "/";
	}

	@Override
	public String save(InputStream file, String filename) {
		byte[] bytes;
		try {
			bytes = file.readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		String ext = filename.substring(filename.indexOf(".") + 1);
		String[] strings = FdfsFileUtil.uploadFile(bytes, ext, null);
		return BASE_URL + StringUtils.join(strings, "/");
	}

	@Override
	public byte[] download(String path) {
		return FdfsFileUtil.downloadFile(path);
	}

	@Override
	public void delete(String path) {
		FdfsFileUtil.deleteFile(path);
	}
}
