package cn.dsttl3.chaohua.slice;

import cn.dsttl3.chaohua.ResourceTable;
import cn.dsttl3.chaohua.bean.WeiBoTokenJson;
import com.google.gson.Gson;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Text;
import ohos.agp.components.webengine.*;
import ohos.media.image.PixelMap;
import ohos.utils.net.Uri;
import okhttp3.*;

import java.io.IOException;

public class WeiBoLoginAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_weibo);
        Text text = (Text) findComponentById(ResourceTable.Id_Text_index);
        WebView myWebView = (WebView) findComponentById(ResourceTable.Id_WebView_index);
        myWebView.getWebConfig().setJavaScriptPermit(true);
        myWebView.setWebAgent(new WebAgent(){

            @Override
            public boolean isNeedLoadUrl(WebView webView, ResourceRequest request) {

                if (request.getRequestUrl().toString().startsWith("sinaweibo")){
                    Intent intent = new Intent();
                    Operation operation = new Intent.OperationBuilder()
                            .withAction("android.intent.action.VIEW")
                            .withUri(Uri.parse(request.getRequestUrl().toString()))
                            .build();
                    intent.setOperation(operation);
                    startAbility(intent);
                    return false;
                }
                if (request.getRequestUrl().toString().startsWith("https://api.dsttl3.cn")){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String code = request.getRequestUrl().toString().substring(28);
                                String YOUR_CLIENT_ID = "3028447309";
                                String YOUR_CLIENT_SECRET = "427b0b70a23e623a51db0bd5eb939e77";
                                String YOUR_REGISTERED_REDIRECT_URI = "https://api.dsttl3.cn";
                                String ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
                                OkHttpClient client = new OkHttpClient();
                                FormBody body = new FormBody.Builder()
                                        .add("client_id", YOUR_CLIENT_ID)
                                        .add("client_secret", YOUR_CLIENT_SECRET)
                                        .add("grant_type", "authorization_code")
                                        .add("redirect_uri", YOUR_REGISTERED_REDIRECT_URI)
                                        .add("code", code).build();
                                Request okRequest = new Request.Builder().url(ACCESS_TOKEN_URL).header("referer","https://api.dsttl3.cn").post(body).build();
                                Call call = client.newCall(okRequest);
                                Response re = call.execute();
                                String s = re.body().string();
                                Gson gson = new Gson();
                                WeiBoTokenJson w = gson.fromJson(s, WeiBoTokenJson.class);
                                getUITaskDispatcher().asyncDispatch(new Runnable() {
                                    @Override
                                    public void run() {
                                        text.setText("登录成功：Token="+s);
                                        Intent intent = new Intent();
                                        intent.setParam("token",w.getAccess_token());
                                        Operation operation = new Intent.OperationBuilder()
                                                .withDeviceId("")
                                                .withBundleName("cn.dsttl3.test")
                                                .withAbilityName("cn.dsttl3.chaohua.MyAbility")
                                                .build();
                                        intent.setOperation(operation);
                                        startAbility(intent);
                                        terminateAbility();
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                return true;
            }

            @Override
            public void onLoadingPage(WebView webView, String url, PixelMap icon) {
                super.onLoadingPage(webView, url, icon);
                text.setText("正在访问：" + url);
            }

            @Override
            public void onPageLoaded(WebView webView, String url) {
                super.onPageLoaded(webView, url);
                String cookie = CookieStore.getInstance().getCookie(url);
                text.setText(url);
            }
        });
        myWebView.load("https://api.weibo.com/oauth2/authorize?client_id=3028447309&response_type=code&forcelogin=false&scope=all&redirect_uri=https%3A%2F%2Fapi.dsttl3.cn%2F");
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }


}
