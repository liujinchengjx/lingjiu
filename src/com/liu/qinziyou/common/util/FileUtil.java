package com.liu.qinziyou.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.commons.fileupload.FileItem;

import com.liu.qinziyou.common.Configuration;
import com.liu.qinziyou.common.Util;


/**
 * <p>Title:</p>
 * <p>Description:文件处理工具</p>
 * <p>创建日期:2013-10-25</p>
 */
public class FileUtil {
	
	public static String STATIC_FILE_PATH=Configuration.getConfigValue("statics.file.basepath");


    public FileUtil() {
    }

        /**
     * 判断一个File对象否是文件。
     * @param file 要判断的File对象。
     * @return 返回判断的结果，如果文件不为空并且文件存在并且该对象是文件则返回true；否则返回false。
     * @author liliang.
     */
    private static boolean isFile(File file) {
        return (file != null) && file.exists() && file.isFile();
    }

    /**
     * 判断一个File对象是否是目录。
     * @param dir 要判断的File对象。
     * @return 返回判断的结果。如果File不为空并且文件存在并且该对象是目录，则返回true；否则返回false。
     * @author liliang.
     */
    private static boolean isDirectory(File dir) {
        return (dir != null) && dir.exists() && dir.isDirectory();
    }
    /**
     * 创建一个新目录
     * @file 要创建的文件目录
     * @return 是否创建成功
     */
    public static final boolean createFolder(File file) {
        boolean result = false;

        try {
            if (file.exists()) {
                file.delete();
            }
            result = file.mkdir();
        } catch (Exception ex) {
            result = false;
            ex.printStackTrace();
        }

        return result;
    }
    /**
     * 创建一个新目录
     * @folderName 要创建的文件目录名
     * @filePath 创建文件的路径
     * @return 是否创建成功
     */
    public static final boolean createFolder(String folderName) {
        boolean result = false;
        File file = new File(folderName.trim());
        try {            
             file.mkdirs();
             result =true;
        } catch (Exception ex) {
            result = false;
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 
     */
    public static String  saveFileToDisk(FileItem fileItem,String fileType) {
    	if(fileItem.getName()!=null && fileItem.getSize()!=0){
    		try{
	    		String fileName = Util.getIDKey()+Util.getExtensionName(fileItem.getName());// 保存文件名
	    		String ymd=DateUtil.getDateTime("yyyyMMdd",0);
	    		String filePath =  "uploadfiles/"+fileType+"/"+ymd+"/"+fileName;
	    		String destFilePath= STATIC_FILE_PATH+"/"+filePath;
	    		File destFile = new File(destFilePath);
	    		if (!destFile.getParentFile().exists()) destFile.getParentFile().mkdirs();
	    		fileItem.write(destFile);
	    		return filePath;
    		}catch (Exception e) {
    			e.printStackTrace();
    			return null;
    		}
    	}
    	return null;
    }  
    /**
	 * 文件拷贝
	 * 
	 * @param sourceFilePath
	 * @param newFilePath
	 */
	public static void copy(String sourceFilePath, String newFilePath) {
		try {
			FileInputStream fileInputStream = new FileInputStream(sourceFilePath);
			FileOutputStream outputStream = new FileOutputStream(newFilePath);

			FileChannel inputChannel = fileInputStream.getChannel();
			FileChannel outputChannel = outputStream.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			Charset charSet = Charset.forName("utf-8");
			// 进行编码解码-->相当于水斗中水的过滤作用
			CharsetDecoder decoder = charSet.newDecoder();
			CharsetEncoder encoder = charSet.newEncoder();

			while (true) {
				buffer.clear();
				CharBuffer charBuffer = decoder.decode(buffer);
				ByteBuffer bb = encoder.encode(charBuffer);
				int k = inputChannel.read(bb);
				if (k == -1) {
					break;
				}
				bb.flip();
				outputChannel.write(bb);
			}
			outputChannel.close();
			inputChannel.close();
			outputStream.close();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
