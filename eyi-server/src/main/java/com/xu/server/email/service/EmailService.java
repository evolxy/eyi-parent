package com.xu.server.email.service;

import com.xu.server.email.pojo.EmailInfo;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/25 10:59
 */
@Component
@Slf4j
public class EmailService {
	private final JavaMailSender jms;

	@Value("${spring.mail.username}")
	private String from = "eyi.adm@qq.com";

	private final FreeMarkerConfigurer fmc;

	public EmailService(JavaMailSender jms, FreeMarkerConfigurer fmc) {
		this.jms = jms;
		this.fmc = fmc;
	}

	public void sendMsg(EmailInfo info, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(info.getSubject());
		message.setFrom(from);
		message.setTo(info.getTo());
		message.setText(text);
		jms.send(message);
	}

	public void sendMsg(Map<String, Object> params, EmailInfo info, String templatePath) {
		try {
			MimeMessage message = jms.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject(info.getSubject());
			helper.setFrom(from);
			helper.setTo(info.getTo());
			Template template = fmc.getConfiguration().getTemplate(templatePath);
			StringWriter sw = new StringWriter();
			template.process(params, sw);
			String html = sw.toString();
			helper.setText(html, true);
			jms.send(message);
		} catch (MessagingException | TemplateException | IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
