package com.xu.server.storage.constant;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/6 11:47
 */

public enum StorageClient {
	/**
	 * MINIO FDFS
	 */
	MINIO(1), FDFS(2);

	StorageClient(int type) {
		this.type = type;
	}

	private final int type;

	public int getType() {
		return type;
	}
}
