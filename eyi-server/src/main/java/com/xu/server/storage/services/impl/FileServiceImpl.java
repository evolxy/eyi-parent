package com.xu.server.storage.services.impl;

import com.xu.server.storage.client.IStorageClient;
import com.xu.server.storage.client.impl.FdfsFileClient;
import com.xu.server.storage.client.impl.MinioFileClient;
import com.xu.server.storage.constant.StorageClient;
import com.xu.server.storage.services.IFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/7 16:28
 */
@Service
public class FileServiceImpl implements IFileService {
	private IStorageClient client;

	@Value("eyi.storage.client")
	private StorageClient storageClient;

	public FileServiceImpl() {
		switch (storageClient) {
			case MINIO:
				client = new MinioFileClient();
				break;
			case FDFS:
				client = new FdfsFileClient();
				break;
			default:
				break;
		}
	}

	@Override
	public String upload(MultipartFile file) {

		return null;
	}

	@Override
	public byte[] download(String path) {
		return new byte[0];
	}

	@Override
	public byte[] download(long id) {
		return new byte[0];
	}

}
