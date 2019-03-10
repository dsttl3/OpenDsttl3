package index;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import com.google.gson.Gson;

import entity.WeiBoJson;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetToken {

	final static String YOUR_CLIENT_ID = "";//从open.weibo.cn获取    App Key
	final static String YOUR_CLIENT_SECRET = "";//从open.weibo.cn获取    App Secret
	final static String YOUR_REGISTERED_REDIRECT_URI = "https://www.dsttl3.cn";//在open.weibo.cn填写的安全域名 用于接收微博授权后返回的code
	final static String ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";//微博认证接口，无需修改

	/**
	 * 微博授权token获取（需打开浏览器进行授权，并输入微博返回的code）
	 * @return token
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public String getWeiBoToken() throws IOException {
		// TODO Auto-generated method stub
		Desktop desktop = Desktop.getDesktop();
		if (desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(new URI(
						"https://api.weibo.com/oauth2/authorize?client_id="+YOUR_CLIENT_ID+"&response_type=code&redirect_uri="+YOUR_REGISTERED_REDIRECT_URI));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.err.println("请在浏览器中对【dsttl3_J】进行授权，并输入返回的链接中code的值：");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		str = br.readLine();
		OkHttpClient client = new OkHttpClient();
		FormBody body = new FormBody.Builder()
				.add("client_id", YOUR_CLIENT_ID)
				.add("client_secret", YOUR_CLIENT_SECRET)
				.add("grant_type", "authorization_code")
				.add("redirect_uri", YOUR_REGISTERED_REDIRECT_URI)
				.add("code", str).build();
		Request request = new Request.Builder().url(ACCESS_TOKEN_URL).post(body).build();
		Call call = client.newCall(request);
		Response re = call.execute();
		String s = re.body().string();
		Gson gson = new Gson();
		WeiBoJson w = gson.fromJson(s, WeiBoJson.class);
		return w.getAccess_token();
	}
}
