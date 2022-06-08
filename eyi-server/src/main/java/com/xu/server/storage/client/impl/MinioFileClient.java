package com.xu.server.storage.client.impl;

import com.xu.server.storage.client.IStorageClient;
import com.xu.server.storage.constant.MinioConstant;
import com.xu.server.storage.utils.MinioUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/7 16:33
 */
@Slf4j
public class MinioFileClient implements IStorageClient {
	@Override
	public String save(InputStream file, String filename) {
		String url = MinioUtils.uploadObject(MinioConstant.BUCKET_NAME, filename, file);
		return url.substring(0, url.indexOf("?"));
	}

	@Override
	public byte[] download(String path) {
		String objName;
		byte[] res = {};
		String separator = "/";
		if (path.endsWith(separator)) {
			path = path.substring(0, path.length() - 2);
		}
		objName = path.substring(path.lastIndexOf(separator) + 1);
		InputStream inputStream = MinioUtils.getObject(MinioConstant.BUCKET_NAME, objName);
		if (inputStream == null) {
			return res;
		}
		try {
			return inputStream.readAllBytes();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return res;
	}

	@Override
	public void delete(String path) {
		String separator = "/";
		if (path.endsWith(separator)) {
			path = path.substring(0, path.length() - 2);
		}
		String[] split = path.split(separator);
		int length = split.length;
		String bucketName = split[length - 2];
		String objName = split[length - 1];
		MinioUtils.deleteObject(bucketName, objName);
	}
}
