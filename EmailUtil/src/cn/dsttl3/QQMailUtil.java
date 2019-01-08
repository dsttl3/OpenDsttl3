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
	 * @param to      �ռ��˵�������
	 * @param title   �ʼ�����
	 * @param content �ʼ�����
	 * @return �ʼ���Ϣ
	 */
	public String SendEmail(String to, String title, String content) {
		String rString = "";
		// �ռ��˵�������
		String toMail = to;
		// �����˵������䣨�Լ���д��
		String fromMail = "";
		// ָ�������ʼ�������Ϊ smtp.qq.com
		String hostServer = "smtp.qq.com"; // QQ �ʼ�������
		// �����������û������Լ���д��
		String userName = "";
		// �������������루��Ȩ�룩���Լ���д��
		String passWord = "";
		// �ʼ�����
		String mailTitleString = title;
		// �ʼ�����
		String mailContentString = content;
		// ��ȡϵͳ����
		Properties properties = System.getProperties();
		// �����ʼ�������
		properties.setProperty("mail.smtp.host", hostServer);
		properties.put("mail.smtp.auth", "true");
		// ��ȡĬ��session����
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, passWord);
			}
		});
		try {
			// ����Ĭ�ϵ� MimeMessage ����
			MimeMessage message = new MimeMessage(session);
			// Set From: ͷ��ͷ�ֶ�
			message.setFrom(new InternetAddress(fromMail));
			// Set To: ͷ��ͷ�ֶ�
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
			// Set Subject: ͷ��ͷ�ֶ�
			message.setSubject(mailTitleString);
			// ������Ϣ��
			message.setText(mailContentString);
			// ������Ϣ
			Transport.send(message);
			// �����ʼ���Ϣ
			rString = "��" + fromMail + "�� ---> ��" + toMail + "����\n----------------------------------------\n��Title��"
					+ mailTitleString + "\n��Content��" + mailContentString;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			rString = "�ʼ����ͷ�������";
		}
		return rString;
	}

	public static void main(String[] args) {
		System.out.println(new QQMailUtil().SendEmail("1@qq.com", "���", "hello world"));
	}
}
