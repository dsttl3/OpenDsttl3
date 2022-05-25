package index;

import java.io.IOException;

/**
 * @author dsttl3
 */
public class Test {
    public static void main(String[] args) {
        try {
            System.out.println(new ShareWeiBo().share());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
