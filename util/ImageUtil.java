package cn.dsttl3.test.util.net;

import ohos.agp.components.Image;
import ohos.app.Context;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {
    private Context context;
    private String url;
    private Image image;

    /**
     * 获取网络图片
     * @param context getContext()
     * @param url 图片url地址
     * @param image 需要显示的image控件
     */
    public ImageUtil(Context context , String url, Image image){
        this.context = context;
        this.url = url;
        this.image = image;
    }

    /**
     * 将图片显示到image上
     */
    public void show(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    ResponseBody body = client.newCall(request).execute().body();
                    InputStream in = body.byteStream();
                    ImageSource imageSource = ImageSource.create(in, null);
                    PixelMap pixelMap =  imageSource.createPixelmap(null);
                    context.getUITaskDispatcher().asyncDispatch(new Runnable() {
                        @Override
                        public void run() {
                            image.setPixelMap(pixelMap);
                            pixelMap.release();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
