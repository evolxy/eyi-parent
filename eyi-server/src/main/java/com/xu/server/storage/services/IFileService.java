package com.xu.server.storage.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/7 16:22
 */

public interface IFileService {
	/**
	 * 上传文件
	 * @param file 页面上传的文件
	 * @return 文件地址
	 */
	String upload(MultipartFile file);

	/**
	 * 根据文件地址下载文件
	 * @param path 文件路径
	 * @return ios
	 */
	byte[] download(String path);

	/**
	 * 根据file info 表中的id下载文件
	 * @param id id
	 * @return ios
	 */
	byte[] download(long id);
}
