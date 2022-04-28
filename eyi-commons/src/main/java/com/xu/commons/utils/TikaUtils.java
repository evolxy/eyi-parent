package com.xu.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/19 11:00
 */
@Slf4j
public class TikaUtils {
	public static String getContentTypeByExtension(String extension) {
		Tika tika = new Tika();
		return tika.detect("test." + extension);
	}

	public static String getContentTypeByFileName(String filename) {
		Tika tika = new Tika();
		return tika.detect(filename);
	}

	public static String getExtByContentType(String contentType) {
		MimeTypes all = MimeTypes.getDefaultMimeTypes();
		MimeType type;
		String res = "";
		try {
			type = all.forName(contentType);
			res = type.getExtension();
		} catch (MimeTypeException e) {
			log.error(e.getMessage(), e);
		}
		return res;
	}
}
