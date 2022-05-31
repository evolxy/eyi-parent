package com.xu.server.admin.user.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码生成工具
 * @author Author
 * @version 0.1
 * Created On 2022/5/31 16:27
 */

public class Captcha {
	private final static String BASE_CODE = "abcdefghijklmnopqrstuvwxyz0123456789";

	private final static String DEFAULT_FORMAT_NAME = "png";

	private final static int DEFAULT_CODE_LENGTH = 4;

	private final static int DEFAULT_LINE_NUMBERS = 10;

	public static String createImgCaptcha(OutputStream os) {
		return createImgCaptcha(os, DEFAULT_CODE_LENGTH, DEFAULT_LINE_NUMBERS, BASE_CODE, DEFAULT_FORMAT_NAME);
	}

	public static String createImgCaptcha(OutputStream os, int codeLen, int lineNum) {
		return createImgCaptcha(os, codeLen, lineNum, BASE_CODE, DEFAULT_FORMAT_NAME);
	}

	public static String createImgCaptcha(OutputStream os, int codeLen, int lineNum, String formatName) {
		return createImgCaptcha(os, codeLen, lineNum, BASE_CODE,formatName);
	}

	/**
	 * 生成四位图片验证码
	 * @param os 验证码图片生成位置
	 * @param baseCode 验证码集合
	 * @param codeLen  验证码长度
	 * @param formatName 验证码文件格式
	 * @return code
	 */
	public static String createImgCaptcha(OutputStream os, int codeLen, int lineNum, String baseCode, String formatName) {
		BufferedImage img = new BufferedImage(80, 32, BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics = img.getGraphics();
		// 设置背景填充色
		graphics.setColor(new Color(239, 239, 239));
		graphics.fillRect(0, 0, 80, 32);

		// 字体和颜色设置
		graphics.setColor(new Color(49, 49, 49));
		graphics.setFont(new Font("Georgia", Font.ITALIC, 30));
		StringBuilder sb = new StringBuilder();
		int length = baseCode.length() - 1;
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < codeLen; i++) {
			int randomIdx = random.nextInt(length);
			sb.append(baseCode.charAt(randomIdx));
		}
		graphics.drawString(sb.toString(), 17, 24);
		for (int i = 0; i < lineNum; i++) {
			Color c = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
			graphics.setColor(c);
			graphics.drawLine(random.nextInt(50), random.nextInt(30), random.nextInt(80), random.nextInt(80));
		}
		try {
			ImageIO.write(img, formatName, os);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return sb.toString();
	}
}
