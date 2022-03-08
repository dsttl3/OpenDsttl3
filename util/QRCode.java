package cn.dsttl3.chaohua.util;

import ohos.agp.components.Image;
import ohos.ai.cv.common.ConnectionCallback;
import ohos.ai.cv.common.VisionManager;
import ohos.ai.cv.qrcode.IBarcodeDetector;
import ohos.app.Context;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import ohos.media.image.common.PixelFormat;

public class QRCode {
    Image image;
    Context context;
    String text;

    public QRCode setImage(Image image) {
        this.image = image;
        return this;
    }

    public QRCode setContext(Context context) {
        this.context = context;
        return this;
    }

    public QRCode setText(String text) {
        this.text = text;
        return this;
    }

    public void show(){
        ConnectionCallback connectionCallback = new ConnectionCallback() {
            @Override
            public void onServiceConnect() {
                //连接成功生成二维码
                createQRCode(text,image);
            }
            @Override
            public void onServiceDisconnect() {
            }
        };
        //初始化，建立与能力引擎的连接
        VisionManager.init(context, connectionCallback);
    }

    private void createQRCode(String barText, Image image){
        //实例化接口，获取二维码侦测器
        IBarcodeDetector barcodeDetector = VisionManager.getBarcodeDetector(context);
        //定义码生成图像的尺寸
        final int SAMPLE_LENGTH = 500;
        //根据图像的大小，分配字节流数组的空间
        byte[] byteArray = new byte[SAMPLE_LENGTH * SAMPLE_LENGTH * 4];
        //调用IBarcodeDetector的detect()方法，根据输入的字符串信息生成相应的二维码图片字节流
        barcodeDetector.detect(barText, byteArray, SAMPLE_LENGTH, SAMPLE_LENGTH);
        //释放侦测器
        barcodeDetector.release();
        //通过SourceOptions指定数据源的格式信息
        ImageSource.SourceOptions srcOpts = new ImageSource.SourceOptions();
        //定义图片格式
        srcOpts.formatHint = "image/png";
        //创建图片源
        ImageSource imgSource= ImageSource.create(byteArray,srcOpts);
        //创建图像解码选项
        ImageSource.DecodingOptions decodingOpts =new ImageSource.DecodingOptions();
        decodingOpts.desiredPixelFormat= PixelFormat.ARGB_8888;
        //通过图片源创建PixelMap
        PixelMap pMap =imgSource.createPixelmap(decodingOpts);
        //赋值到图片标签
        image.setPixelMap(pMap);
        //释放资源
        barcodeDetector.release();
        imgSource.release();
        if(pMap!=null)
        {
            pMap.release();
        }
        //断开与能力引擎的连接
        VisionManager.destroy();
    }
}
