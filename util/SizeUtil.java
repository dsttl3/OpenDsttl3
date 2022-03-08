package baidu.util;

public class SizeUtil {
    /**
     * 字节转kb/mb/gb
     * @param size
     * @return
     */
    public  String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        }
        if (size < (1024 * 1024)) {
            return String.valueOf(size / 1024) + "KB";
        }
        if (size < 1024 * 1024 * 1024) {
            return String.valueOf((size / 1024 / 1024)) + "." + String.valueOf(((size / 1024 / 1024) % 100)) + "MB";
        } else {
            return String.valueOf((size / 1024 / 1024 / 1024)) + "." + String.valueOf(((size / 1024 / 1024 / 1024) % 100)) + "GB";
        }
    }
}
