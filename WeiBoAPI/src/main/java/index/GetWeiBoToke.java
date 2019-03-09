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

/**
 * 微博发布接口（使用微博第三方分享接口进行发布）
 * @author dsttl3
 *
 */
public class GetWeiBoToke {
	final static String YOUR_CLIENT_ID = "";//从open.weibo.cn获取
	final static String YOUR_CLIENT_SECRET = "";
	final static String YOUR_REGISTERED_REDIRECT_URI = "https://www.dsttl3.cn";
	final static String ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		Desktop desktop = Desktop.getDesktop();
		if (desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(new URI(
						"https://api.weibo.com/oauth2/authorize?client_id=164595604&response_type=code&redirect_uri="+YOUR_REGISTERED_REDIRECT_URI));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.println("请在浏览器中对【dsttl3_J】进行授权，并输入返回的链接中code的值：");
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
		System.out.println("access_token:" + w.getAccess_token());
		System.out.println("请输入发布的内容");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		String str2 = null;
		str2 = br2.readLine();
		OkHttpClient client2 = new OkHttpClient();
		FormBody body2 = new FormBody.Builder()
				.add("access_token", w.getAccess_token())
				.add("status", str2+YOUR_REGISTERED_REDIRECT_URI)
				.build();
		Request request2 = new Request.Builder().url("https://api.weibo.com/2/statuses/share.json").post(body2).build();
		Call call2 = client2.newCall(request2);
		Response re2 = call2.execute();
		String s2 = re2.body().string();
		System.out.println(s2);
	}
}
