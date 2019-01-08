package xyz.dstt;

import java.text.SimpleDateFormat;

public class SmsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(System.currentTimeMillis());
		Sms.SmsTools("dstt", "1000000",time);
	}

}
