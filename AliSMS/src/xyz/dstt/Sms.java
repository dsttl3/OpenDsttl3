package xyz.dstt;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class Sms {
	public static void SmsTools(String userName, String phone, String time) {
		String serverUrl = "http://gw.api.taobao.com/router/rest";
		String appKey = "";//Key
		String appSecret = "";//Secret
		String extend = "";
		String smsType = "normal";
		String smsFreeSignName = "";//短信签名
		String smsTemplateCode = "";//短信模板ID
		TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(extend);
		req.setSmsType(smsType);
		req.setSmsFreeSignName(smsFreeSignName);
		req.setSmsParamString("{\"user_name\":\"" + userName + "\",\"time\":\"" + time + "\"}");//短信模板变量替换JSON串  （根据自己的模版修改）
		req.setRecNum(phone);//接收短信的手机号码
		req.setSmsTemplateCode(smsTemplateCode);
		try {
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			System.out.println(rsp.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
