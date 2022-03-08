package cn.dsttl3.test.fraction;


import cn.dsttl3.test.ResourceTable;
import cn.dsttl3.test.util.SaveImage;
import cn.dsttl3.test.util.net.ImageNetWork;
import cn.dsttl3.test.util.weibo.Login.GetQRImgUrl;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.media.image.PixelMap;
import ohos.utils.net.Uri;

public class MainFraction extends Fraction {

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        Image image = (Image) getFractionAbility().findComponentById(ResourceTable.Id_Image_WeiBo_QR);
        Text text = (Text) getFractionAbility().findComponentById(ResourceTable.Id_text_qrloding);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String qrUrl = new GetQRImgUrl().getQR().getData().getImageUrl();
                PixelMap pixelMap = new ImageNetWork(qrUrl).start();
                getUITaskDispatcher().asyncDispatch(new Runnable() {
                    @Override
                    public void run() {
                        image.setPixelMap(pixelMap);
                        text.setText("请扫描二维码");
                        pixelMap.release();
                        image.setClickedListener(new Component.ClickedListener() {
                            @Override
                            public void onClick(Component component) {
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                intent.setUri(Uri.parse("sinaweibo://qrcode"));
                                intent.addFlags(Intent.FLAG_ABILITY_NEW_MISSION);
                                getFractionAbility().startAbility(intent);
                            }
                        });
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onActive() {
        super.onActive();
    }

    @Override
    protected Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent intent) {
        Component component = scatter.parse(ResourceTable.Layout_fraction_main, container, false);
        return component;
    }


    @Override
    protected void onStop() {
        super.onStop();
    }
}
