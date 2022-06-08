package com.xu.server.storage.services;

import com.xu.server.base.service.IBaseService;
import com.xu.server.storage.pojo.entity.FileInfo;
import com.xu.server.storage.pojo.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/7 16:22
 */

public interface IFileService extends IBaseService<FileInfo> {
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
	FileVo download(String path);

	/**
	 * 根据file info 表中的id下载文件
	 * @param id id
	 * @return ios
	 */
	FileVo download(long id);

	/**
	 * 根据id删除
	 * @param id id
	 */
	void removeById(long id);

	/**
	 * 根据存储位置进行删除
	 * @param path path
	 */
	void removeByPath(String path);
}
