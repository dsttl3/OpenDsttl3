package cn.dsttl3.weibo.weibot.cookies;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import cn.dsttl3.weibo.weibot.entity.ConfigEntity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UpdateCookies {
	/**
	 * 获取cookie的在线状态，并更新XSRF-TOKEN
	 * 
	 * @param cookie m.weibo.cn 的 SUB + _T_WM
	 * @throws IOException
	 */
	public String updateCookie(String cookie) {
		try {
			String url = "https://m.weibo.cn/api/config";
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(url).header("X-Requested-With", "XMLHttpRequest")
					.header("cookie", cookie).build();
			Response response = client.newCall(request).execute();
			String configJson = response.body().string();
			ConfigEntity config = new Gson().fromJson(configJson, ConfigEntity.class);
			if (config.getData() != null) {
				if (config.getData().isLogin()) {
					return config.getData().getSt();
				} else {
					System.err.println("账号不在线");
					return null;
				}
			} else {
				return null;
			}
		} catch (IOException e) {
			// 错误处理
			e.printStackTrace();
			System.err.println("token获取失败");
			return null;
		}
	}

	/**
	 * 获取st
	 * 
	 * @param cookie
	 * @return
	 */
	public String getST(String cookie) {
		return updateCookie(cookie);
	}

	/**
	 * 获取XSRF-TOKEN
	 * 
	 * @param cookie
	 * @return
	 */
	public String getToken(String cookie) {
		return "XSRF-TOKEN=" + updateCookie(cookie);
	}

	/**
	 * 获取 _T_WM
	 * 
	 * @param sub
	 * @return
	 */
	public String getTWM(String sub) {
		try {
			String url = "https://m.weibo.cn";
			String twm = null;
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(url).header("X-Requested-With", "XMLHttpRequest").header(
					"cookie",sub)
					.build();
			Response response = client.newCall(request).execute();
			List<String> cookies = response.headers().values("Set-Cookie");
			for (String string : cookies) {
				if (string.indexOf("_T_WM") == 0) {
					twm = string.split(";")[0];
					break;
				} else {
					twm = null;
				}
			}
			return twm;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
