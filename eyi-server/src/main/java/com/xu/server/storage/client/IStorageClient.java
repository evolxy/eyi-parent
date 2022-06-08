package com.xu.server.storage.client;

import java.io.InputStream;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/7 16:29
 */

public interface IStorageClient {
	/**
	 * 保存文件
	 * @param file 文件流
	 * @param filename 文件名
	 * @return path
	 */
	String save(InputStream file, String filename);

	/**
	 * 下载
	 * @param path path
	 * @return byte 数组
	 */
	byte[] download(String path);

	/**
	 * 根据存储路径删除
	 * @param path 路径
	 */
	void delete(String path);
}
