package index;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShareWeiBo {
	final static String YOUR_REGISTERED_REDIRECT_URI = "https://www.dsttl3.cn";//安全域名
	final static String WEIBO_SHARE_API_URL = "https://api.weibo.com/2/statuses/share.json";//微博第三方分享接口

	/**
	 * 微博发布（使用微博第三方分享发布接口）
	 * @return 发布的微博数据
	 * @throws IOException
	 */
	public String share() throws IOException {
		// TODO Auto-generated method stub
		String token = new GetToken().getWeiBoToken();
		if (token != null && !token.equals("null")) {
			System.out.println("请输入发布的内容");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str = null;
			str = br.readLine();
			OkHttpClient client = new OkHttpClient();
			FormBody body = new FormBody.Builder().add("access_token", token)
					.add("status", str + YOUR_REGISTERED_REDIRECT_URI).build();
			Request request = new Request.Builder().url(WEIBO_SHARE_API_URL).post(body).build();
			Call call = client.newCall(request);
			Response re = call.execute();
			String s = re.body().string();
			return s;
		} else {
			return "token 获取失败！";
		}
	}
}
