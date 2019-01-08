package cn.dsttl3;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class QQMailUtil {
	/**
	 * 
	 * @param to      收件人电子邮箱
	 * @param title   邮件主题
	 * @param content 邮件内容
	 * @return 邮件信息
	 */
	public String SendEmail(String to, String title, String content) {
		String rString = "";
		// 收件人电子邮箱
		String toMail = to;
		// 发件人电子邮箱（自己填写）
		String fromMail = "";
		// 指定发送邮件的主机为 smtp.qq.com
		String hostServer = "smtp.qq.com"; // QQ 邮件服务器
		// 发件人邮箱用户名（自己填写）
		String userName = "";
		// 发件人邮箱密码（授权码）（自己填写）
		String passWord = "";
		// 邮件主题
		String mailTitleString = title;
		// 邮件内容
		String mailContentString = content;
		// 获取系统属性
		Properties properties = System.getProperties();
		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", hostServer);
		properties.put("mail.smtp.auth", "true");
		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, passWord);
			}
		});
		try {
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);
			// Set From: 头部头字段
			message.setFrom(new InternetAddress(fromMail));
			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
			// Set Subject: 头部头字段
			message.setSubject(mailTitleString);
			// 设置消息体
			message.setText(mailContentString);
			// 发送消息
			Transport.send(message);
			// 返回邮件信息
			rString = "【" + fromMail + "】 ---> 【" + toMail + "】：\n----------------------------------------\n【Title】"
					+ mailTitleString + "\n【Content】" + mailContentString;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			rString = "邮件发送发生错误";
		}
		return rString;
	}

	public static void main(String[] args) {
		System.out.println(new QQMailUtil().SendEmail("1@qq.com", "你好", "hello world"));
	}
}
