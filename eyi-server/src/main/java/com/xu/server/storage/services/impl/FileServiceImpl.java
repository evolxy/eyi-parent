package com.xu.server.storage.services.impl;

import com.xu.server.base.service.IBaseService;
import com.xu.server.base.service.impl.BaseServiceImpl;
import com.xu.server.storage.client.IStorageClient;
import com.xu.server.storage.pojo.entity.FileInfo;
import com.xu.server.storage.pojo.vo.FileVo;
import com.xu.server.storage.repository.FileInfoRepository;
import com.xu.server.storage.services.IFileService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/7 16:28
 */
@Service
@AllArgsConstructor
public class FileServiceImpl extends BaseServiceImpl<FileInfo, FileInfoRepository> implements IFileService, IBaseService<FileInfo> {
	private final IStorageClient client;

	@Override
	public String upload(MultipartFile file) {
		String storagePath = "";
		try {
			storagePath = client.save(file.getInputStream(), file.getOriginalFilename());
			if (StringUtils.isBlank(storagePath)) {
				return storagePath;
			}
			FileInfo fileInfo = convertToFileInfo(file);
			fileInfo.setStorePath(storagePath);
			saveOrUpdate(fileInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return storagePath;
	}

	private FileInfo convertToFileInfo(MultipartFile file) {
		FileInfo fileInfo = new FileInfo();
		String filename = file.getOriginalFilename();
		if (StringUtils.isBlank(filename)) {
			return fileInfo;
		}
		String ext = filename.substring(filename.indexOf(".") + 1);
		fileInfo.setOriginName(filename);
		fileInfo.setExtension(ext);
		return fileInfo;
	}

	@Override
	public FileVo download(String path) {
		FileInfo entity = new FileInfo();
		entity.setStorePath(path);
		List<FileInfo> infos = list(entity);
		if (CollectionUtils.isNotEmpty(infos)) {
			entity = infos.get(0);
		} else {
			return null;
		}
		byte[] bytes = client.download(path);
		return getFileVo(entity, bytes);
	}

	@NotNull
	private FileVo getFileVo(FileInfo entity, byte[] content) {
		FileVo vo = new FileVo();
		vo.setContent(content);
		vo.setFilename(entity.getOriginName());
		vo.setExt(entity.getExtension());
		return vo;
	}

	@Override
	public FileVo download(long id) {
		FileInfo info = getById(id);
		if (info!=null) {
			byte[] content = client.download(info.getStorePath());
			return getFileVo(info, content);
		}
		return null;
	}

	@Override
	public void removeById(long id) {
		FileInfo info = getById(id);
		if (info==null) {
			return;
		}
		String storePath = info.getStorePath();
		removeByPath(storePath);
	}

	@Override
	public void removeByPath(String path) {
		client.delete(path);
	}

}
