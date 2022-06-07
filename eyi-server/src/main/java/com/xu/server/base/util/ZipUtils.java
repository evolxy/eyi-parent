package com.xu.server.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/23 14:03
 */

public class ZipUtils {
	/**
	 * 压缩
	 *
	 * @param path      添加文件/文件夹到压缩流
	 * @param file      要压缩的文件/文件夹
	 * @param zos       zos
	 * @param isKeepDir 保持目录结构
	 * @throws IOException io
	 */
	public static void zip(String path, File file, ZipOutputStream zos, boolean isKeepDir) throws IOException {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				for (File f : files) {
					zip(path + "/" + file.getName(), f, zos, isKeepDir);
				}
			} else if (files != null) {
				if (isKeepDir) {
					// 保留文件结构
					zos.putNextEntry(new ZipEntry(path + "/" + file.getName() + "/"));
					zos.closeEntry();
				}
			}
		} else {
			String entryName = isKeepDir ? path == null || path.length() <= 0 ? file.getName() : path + "/" + file.getName() : file.getName();
			zos.putNextEntry(new ZipEntry(entryName));
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			while (fis.read(buffer) > -1) {
				zos.write(buffer);
			}
			zos.closeEntry();
		}
	}

	/**
	 * 压缩
	 *
	 * @param file     添加文件到压缩流
	 * @param filename 文件名
	 * @param zos      zos
	 * @throws IOException io
	 */
	public static void zipFile(String filename, byte[] file, ZipOutputStream zos) throws IOException {
		boolean isEmptyFile = file == null || file.length < 1;
		zos.putNextEntry(new ZipEntry(filename));
		if (!isEmptyFile) {
			zos.write(file);
		}
		zos.closeEntry();
	}

}
