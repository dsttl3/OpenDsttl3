

public class XingQi {
    public static void main(String[] args) {
        System.out.println(new XingQi().getWeekString(100));
        System.out.println(new XingQi().getWeekString(38));
    }
    private String getWeekString(int w) {
        if (w == 0){
            return "还没有选择";
        }else if(w > 127){
            return "数值错误。";
        }
        StringBuilder str = new StringBuilder("每周");
        String[] string = { "日", "一", "二", "三", "四", "五", "六" };
        boolean[] xq = new boolean[7];
        for (int i = 0; i < 7; i++) {
            if ((w & (1 << i)) > 0) {
                xq[i] = true;
            }
            if ((w & (1 << i)) > 0) {
                str.append(string[i]).append("、");
            }
        }
        String s = str.substring(0, str.length() - 1);
        for (boolean b : xq){
            System.out.print(b + "\t");
        }
        System.out.println();
        return s;
    }
}
