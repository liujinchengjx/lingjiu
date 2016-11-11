package com.liu.qinziyou.common.upload;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.util.CommonUtil;

public class ImgUtil {

	private static final Log logger = LogFactory.getLog(ImgUtil.class);

	public ImgUtil() {
	}

	
	public static void main(String[] args) {
		/*ImgUtil.reduceImg("d:/0.jpg", "d:/2.jpg", 67, 106);
		ImgUtil.reduceImg1("d:/0.jpg", "d:/3.jpg", 67, 106);
		ImgUtil.reduceImg2("d:/0.jpg", "d:/4.jpg", 67, 106);*/
		ImgUtil.reduceImg("f:/test/11.png", "f:/test/11_1.png", 347,260);
		ImgUtil.reduceImg("f:/test/11.png", "f:/test/11_2.png", 188,141);
		ImgUtil.reduceImg("f:/test/11.png", "f:/test/11_3.png", 130,98);
		ImgUtil.reduceImg("f:/test/11.png", "f:/test/11_4.png", 100,75);
		ImgUtil.reduceImg("f:/test/11.png", "f:/test/11_5.png", 45, 34);
		
		ImgUtil.reduceImg("f:/test/1.jpeg", "f:/test/1_1.jpeg", 347,260);
		ImgUtil.reduceImg("f:/test/1.jpeg", "f:/test/1_2.jpeg", 188,141);
		ImgUtil.reduceImg("f:/test/1.jpeg", "f:/test/1_3.jpeg", 130,98);
		ImgUtil.reduceImg("f:/test/1.jpeg", "f:/test/1_4.jpeg", 100,75);
		ImgUtil.reduceImg("f:/test/1.jpeg", "f:/test/1_5.jpeg", 45, 34);
	}

	public final static void writeImage(BufferedImage in, File out,
			float compressionQuality) {
		ImageWriter writer = (ImageWriter) ImageIO.getImageWritersByFormatName(
				"jpg").next();

		ImageOutputStream ios = null;
		try {
			ios = ImageIO.createImageOutputStream(out);
			writer.setOutput(ios);
			ImageWriteParam param = new JPEGImageWriteParam(Locale.getDefault());
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			// param.setCompressionMode(ImageWriteParam.MODE_DEFAULT);
			param.setCompressionQuality(compressionQuality);// 0-1

			writer.write(null, new IIOImage(in, null, null), param);

		} catch (IOException ex) {
			logger.error("writeImage exception:", ex);
		} finally {
			if (ios != null) {
				try {
					ios.flush();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
				writer.dispose();
				try {
					ios.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
				ios = null;
			}
		}

	}

	

	public static void reduceImg(String imgsrc, String imgdist, int widthdist,
			int heightdist) {
		String ext = CommonUtil.getExtensionName(imgsrc).replace(".", "");//图片后缀
		try {

			File srcfile = new File(imgsrc);
			if (!srcfile.exists()) {
				return;
			}

			BufferedImage src = ImageIO.read(srcfile);
			
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			
			float wh = (float) width / (float) height;

			BufferedImage tag = null;
			float tmp_height=0.0f;
			float tmp_width=0.0f;
			if (wh > 1) {//如果比例大于1以目标宽度为准，高度按原图比例缩放
				tmp_width = widthdist;//以目标宽度为准
				tmp_height = (float) widthdist / wh;
				if(src.getType() == 0){
					 tag = new BufferedImage(widthdist,
							(int) tmp_height, 1);
				}else{
				    tag = new BufferedImage(widthdist,
							(int) tmp_height, src.getType());
				}
				
				
			} else {//如果比例小于1，以目标高度为准，宽度按原图比例缩放
				tmp_height = heightdist;//以目标高度为准
				tmp_width = (float) heightdist * wh;
				
				if(src.getType() == 0){
					 tag = new BufferedImage((int) tmp_width,
							 heightdist, 1);
				}else{
				    tag = new BufferedImage((int) tmp_width,
				    		heightdist, src.getType());
				}
			}
			tag.getGraphics().drawImage(
					src.getScaledInstance((int)tmp_width, (int) tmp_height,
							Image.SCALE_SMOOTH), 0, 0, null);//生成新的图片
			FileOutputStream out = new FileOutputStream(imgdist);
			ImageIO.write(tag, ext, out);//输出
			out.close();
		} catch (IOException ex) {
			logger.error(ex);
		}
	}

	

	public static void delFile(String filePath) {
		File picFile = new File(filePath);
		if (picFile.exists()) {
			picFile.delete();
		}
		picFile = null;
	}

	public static void copyFile(String src, String to) {
		File srcFile = new File(src);
		if (srcFile.exists()) {
			try {
				File toFile = new File(to);
				if (!toFile.exists())
					toFile.createNewFile();

				FileInputStream bw = new FileInputStream(srcFile);
				OutputStream bos = new FileOutputStream(new File(to));
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = bw.read(buffer, 0, 8192)) != -1) {
					bos.write(buffer, 0, bytesRead);
				}
				bos.close();
				bw.close();
				bos = null;
				bw = null;
			} catch (FileNotFoundException ex) {
				logger.error(ex);
			} catch (IOException ex) {
				logger.error(ex);
			}
		}
	}
	
	
}
