package com.xu.server.base.util;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/22 15:50
 */

public class FieldNameUtil {
	private static final Pattern LINE = Pattern.compile("_(\\w)");
	private static final Pattern HUMP = Pattern.compile("[A-Z]");

	public static String humpToLine(String name) {
		Matcher matcher = HUMP.matcher(name);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase(Locale.ROOT));
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static String lineToHump(String name) {
		Matcher matcher = LINE.matcher(name);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase(Locale.ROOT));
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
