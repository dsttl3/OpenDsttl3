package cn.dsttl3;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.fit.cssbox.demo.ImageRenderer;
import org.xml.sax.SAXException;

/**
 * 网页转图片
 * @author dsttl3
 *
 */

public class ImgDownload {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			ImageRenderer render = new ImageRenderer();
			System.out.println("start...");
			String url = "https://www.dsttl3.cn/?p=1";
			FileOutputStream out = new FileOutputStream(new File("html_img.png"));
			render.setWindowSize(new Dimension(1366, 768), true);
			render.renderURL(url, out, ImageRenderer.Type.PNG);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("OK");
	}

}
