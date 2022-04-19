package com.xu.commons.utils;

import org.apache.tika.Tika;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/4/19 11:00
 */

public class TikaUtils {
    public static String getContentTypeByExtension(String extension) {
        Tika tika = new Tika();
        return tika.detect("test." + extension);
    }

    public static String getContentTypeByFileName(String filename) {
        Tika tika = new Tika();
        return tika.detect(filename);
    }
}
