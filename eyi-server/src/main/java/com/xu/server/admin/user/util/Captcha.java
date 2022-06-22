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

	public static void createImgCaptcha(OutputStream os, String code) {
		createImgCaptcha(os,  DEFAULT_LINE_NUMBERS,  DEFAULT_FORMAT_NAME, code);
	}

	public static String createImgCaptcha(OutputStream os) {
		String captcha = generateCaptcha(BASE_CODE, DEFAULT_CODE_LENGTH);
		return createImgCaptcha(os,  DEFAULT_LINE_NUMBERS,  DEFAULT_FORMAT_NAME, captcha);
	}

	public static String createImgCaptcha(OutputStream os, int codeLen, int lineNum) {
		String captcha = generateCaptcha(BASE_CODE, codeLen);
		return createImgCaptcha(os,  lineNum,  DEFAULT_FORMAT_NAME, captcha);
	}

	public static String createImgCaptcha(OutputStream os, int codeLen, int lineNum, String formatName) {
		String captcha = generateCaptcha(BASE_CODE, codeLen);
		return createImgCaptcha(os,  lineNum,  formatName, captcha);
	}

	/**
	 * 生成四位图片验证码
	 * @param os 验证码图片生成位置
	 * @param code 验证码
	 * @param formatName 验证码文件格式
	 * @return code
	 */
	public static String createImgCaptcha(OutputStream os,  int lineNum, String formatName, String code) {
		BufferedImage img = new BufferedImage(80, 32, BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics = img.getGraphics();
		// 设置背景填充色
		graphics.setColor(new Color(239, 239, 239));
		graphics.fillRect(0, 0, 80, 32);

		// 字体和颜色设置
		graphics.setColor(new Color(49, 49, 49));
		graphics.setFont(new Font("Georgia", Font.ITALIC, 30));
		Random random = new Random(System.currentTimeMillis());

		graphics.drawString(code, 5, 24);
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
		return code;
	}

	public static String generateCaptcha(String baseCode, int codeLen) {
		Random random = new Random(System.currentTimeMillis());
		StringBuilder sb = new StringBuilder();
		int length = baseCode.length() - 1;
		for (int i = 0; i < codeLen; i++) {
			int randomIdx = random.nextInt(length);
			sb.append(baseCode.charAt(randomIdx));
		}
		return sb.toString();
	}

	public static String generateCaptcha() {
		return generateCaptcha(BASE_CODE, DEFAULT_CODE_LENGTH);
	}
}
