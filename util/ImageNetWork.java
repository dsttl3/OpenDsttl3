package cn.dsttl3.test.util.net;

import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import java.io.IOException;
import java.io.InputStream;

public class ImageNetWork {
    private String url;
    public ImageNetWork(String url) {
        this.url = url;
    }
    public  PixelMap start() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    ResponseBody body = client.newCall(request).execute().body();
                    InputStream in = body.byteStream();
                    ImageSource imageSource = ImageSource.create(in, null);
                    return imageSource.createPixelmap(null);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
    }
}
