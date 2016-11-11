package com.liu.qinziyou.common.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.Configuration;
import com.liu.qinziyou.common.util.CommonUtil;
import com.liu.qinziyou.common.util.DateUtil;
import com.liu.qinziyou.common.util.FileUtil;

/**
 * 文件上传帮助类。 这个类是个单例
 * 
 * @author liujc
 * 
 *         2013-3-20
 */
public class FileUploadHelper {

	public static enum FileType {
		MEMBER_FILE("1"), // 会员相关的资料
		PRODUCT_FILE("2"), // 发布产品时通过编辑插件上传的图片/flash
		ADVERTISE_FILE("3"), // 广告类型上传的图片/flash
		NEWS_FILE("4"), // 资讯相关的资料
		ACTIVITY_FILE("5"); // 活动的资料

		private String value;

		private FileType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	protected static final Log logger = LogFactory.getLog(FileUploadHelper.class);
	public static String STATICS_BASE_PATH;// 这个基础路径从配置文件中取
	private static String PRODUCT_PIC_PATH = "/productpics";// 发布产品时上传的5张图片
	private static String ACTIVITY_PIC_PATH = "/activitypics";// 发布活动时的封面图和活动图
	public static String PRODUCT_PIC_TEMP_PATH = "/productpics/temp";// 发布产品时图片临时存储位置
	private static String UPLOAD_FILE_PATH = "/uploadfiles";// 于存放会员相关的资料图片、发布产品时通过编辑插件上传的图片/flash等
	public static String TEMP_FILE_PATH = "/tempfile";//上传临时文件存放地

	private static String UPLOAD_MEMBER_FILE_PATH = UPLOAD_FILE_PATH + "/members";// 存放会员相关的资料图片
	private static String UPLOAD_PRODUCT_FILE_PATH = UPLOAD_FILE_PATH + "/productfiles";// 发布产品时通过编辑插件上传的图片/flash
	private static String UPLOAD_ADVERTISE_FILE_PATH = UPLOAD_FILE_PATH + "/advertise";// 于存放广告类型上传的图片/flash
	private static String UPLOAD_DIGINEWS_FILE_PATH = UPLOAD_FILE_PATH + "/news";// 存放资讯相关的资料
	private static String UPLOAD_ACTIVITY_FILE_PATH = UPLOAD_FILE_PATH + "/activity";// 存放通过编辑差距上传的图片、文件等
	
	private static FileUploadHelper fileUploadHelper;

	public static FileUploadHelper getInstance() {
		if (fileUploadHelper == null) {
			fileUploadHelper = new FileUploadHelper();
		}
		return fileUploadHelper;
	}

	private FileUploadHelper() {
		STATICS_BASE_PATH = Configuration.getConfigValue("STATICS_BASE_PATH");
	}
	
	/**
	 * 上传活动图片到服务器
	 * @param inFileBytes
	 * @param ext
	 * @param activityID 活动ID
	 * @param picType 图片类型 1封面图 2活动图
	 * @return
	 * @throws Exception
	 */
	public String uploadActivityPicFile(byte[] inFileBytes, String ext,String activityId,String picType) throws Exception {
		final String filename = getFileName(ext);
		String destFilePathSub;
		try {
			destFilePathSub = this.getActivityPicDir(activityId)+ filename;
			String destFilePath = STATICS_BASE_PATH + destFilePathSub;
			File destFile = new File(destFilePath);
			if (!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			saveUpFile(inFileBytes, destFile);
			if("1".equals(picType)){//封面图
				ImgUtil.reduceImg(destFile.getAbsolutePath(), destFile.getAbsolutePath().replaceAll("\\." + ext, "\\_480_360." + ext),
						480, 360);
				ImgUtil.reduceImg(destFile.getAbsolutePath(), destFile.getAbsolutePath().replaceAll("\\." + ext, "\\_360_270." + ext),
						360, 270);
				destFilePathSub = destFilePathSub .replaceAll("\\." + ext, "\\_360_270." + ext);
			}
			if("2".equals(picType)){//活动图
				ImgUtil.reduceImg(destFile.getAbsolutePath(), destFile.getAbsolutePath().replaceAll("\\." + ext, "\\_300_200." + ext),
						300, 200);
				destFilePathSub = destFilePathSub .replaceAll("\\." + ext, "\\_300_200." + ext);
			}
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("保存文件失败");
		}
		return destFilePathSub;
	}

	/**
	 * 上传产品图片文件到服务器
	 * 
	 * @param inFileBytes
	 *            产品对应图片的byte
	 * @return
	 */
	public String uploadProductPicFile(byte[] inFileBytes, String ext) throws Exception {
		final String filename = getFileName(ext);
		String destFilePathSub;
		try {
			destFilePathSub = getProductPicDir() + filename;
			String destFilePath = STATICS_BASE_PATH + destFilePathSub;
			File destFile = new File(destFilePath);
			if (!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			saveUpFile(inFileBytes, destFile);
			ImgUtil.reduceImg(destFile.getAbsolutePath(), destFile.getAbsolutePath().replaceAll("\\." + ext, "\\_1." + ext),
					800, 800);
			ImgUtil.reduceImg(destFile.getAbsolutePath(), destFile.getAbsolutePath().replaceAll("\\." + ext, "\\_2." + ext),
					500, 500);
			ImgUtil.reduceImg(destFile.getAbsolutePath(), destFile.getAbsolutePath().replaceAll("\\." + ext, "\\_3." + ext),
					275, 275);
			ImgUtil.reduceImg(destFile.getAbsolutePath(), destFile.getAbsolutePath().replaceAll("\\." + ext, "\\_4." + ext),
					100, 100);
			ImgUtil.reduceImg(destFile.getAbsolutePath(), destFile.getAbsolutePath().replaceAll("\\." + ext, "\\_5." + ext),
					50, 50);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("保存文件失败");
		}
		return destFilePathSub;
	}
	
	/**
	 * 根据临时文件创建实际文件并删除临时文件
	 * 
	 * @param tempPath
	 */
	public String createRealProductPicFieFromTemp(String tempPath) throws Exception{
		String result = "";
		try {
			String realPath = this.getRealPath(tempPath);
			result = realPath;
			
			String realPicPath = STATICS_BASE_PATH + realPath;
			String tempPicPath = STATICS_BASE_PATH + tempPath;
			File realFile = new File(realPicPath);
			String ext = CommonUtil.getExtensionName(tempPath).replace(".", "");
			if (!realFile.getParentFile().exists())
				realFile.getParentFile().mkdirs();
			FileUtil.copy(tempPicPath, realPicPath);
			
			//删除临时文件
			this.disposePictureForDeleteAndReset(tempPath);
			
			ImgUtil.reduceImg(realFile.getAbsolutePath(), realFile.getAbsolutePath().replaceAll("\\." + ext, "\\_1." + ext),
					800, 800);
			ImgUtil.reduceImg(realFile.getAbsolutePath(), realFile.getAbsolutePath().replaceAll("\\." + ext, "\\_2." + ext),
					500, 500);
			ImgUtil.reduceImg(realFile.getAbsolutePath(), realFile.getAbsolutePath().replaceAll("\\." + ext, "\\_3." + ext),
					275, 275);
			ImgUtil.reduceImg(realFile.getAbsolutePath(), realFile.getAbsolutePath().replaceAll("\\." + ext, "\\_4." + ext),
					100, 100);
			ImgUtil.reduceImg(realFile.getAbsolutePath(), realFile.getAbsolutePath().replaceAll("\\." + ext, "\\_5." + ext),
					50, 50);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("保存文件失败");
		}
		return result;
	}
	
	/**
	 * 上传产品图片文件到服务器临时文件夹
	 * 
	 * @param inFileBytes
	 *            产品对应图片的byte
	 * @return
	 */
	public String uploadProductPicTempFile(byte[] inFileBytes, String ext) throws Exception {
		final String filename = getFileName(ext);
		String destFilePathSub;
		try {
			destFilePathSub = getProductPicTempDir() + filename;
			String destFilePath = STATICS_BASE_PATH + destFilePathSub;
			File destFile = new File(destFilePath);
			if (!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			saveUpFile(inFileBytes, destFile);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("保存文件失败");
		}
		return destFilePathSub;
	}
	
	/**
	 * 上传文件到服务器临时文件夹
	 * 
	 * @param inFileBytes
	 * @param ext
	 * @return
	 * @throws Exception
	 */
	public String uploadTempFile(byte[] inFileBytes, String ext) throws Exception {
		final String filename = getFileName(ext);
		String destFilePathSub;
		try {
			destFilePathSub = getTempfileDir() + filename;
			String destFilePath = STATICS_BASE_PATH + destFilePathSub;
			File destFile = new File(destFilePath);
			if (!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			saveUpFile(inFileBytes, destFile);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("保存文件失败");
		}
		return destFilePathSub;
	}
	
	
	
	/**
	 * 删除图片的处理
	 * 
	 * @param oldPath
	 * @param picPath
	 * @throws Exception
	 */
	public void disposePictureForDeleteAndReset(String picPath) throws Exception {
		try {
			if (null != picPath && !"".equals(picPath)) {
				delProductPicFile(picPath, CommonUtil.getExtensionName(picPath).replace(".", ""));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("删除图片发生异常", e);
		}
	}
	
	public String uploadProductClassPicFile(byte[] inFileBytes, String ext) throws Exception {
		final String filename = getFileName(ext);
		String destFilePathSub;
		try {
			destFilePathSub = getProductPicDir() + filename;
			String destFilePath = STATICS_BASE_PATH + destFilePathSub;
			File destFile = new File(destFilePath);
			if (!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			saveUpFile(inFileBytes, destFile);
			ImgUtil.reduceImg(destFile.getAbsolutePath(), destFile.getAbsolutePath().replaceAll("\\." + ext, "\\_1." + ext),
					100, 100);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("保存文件失败");
		}
		return destFilePathSub;
	}
	
	

	/**
	 * 删除图片
	 * 
	 * @param relativePath
	 *            图片的相对路径
	 * @throws Exception
	 */
	public void delProductPicFile(String relativePath, String ext) throws Exception {

		String picPath1 = relativePath.replaceAll("\\." + ext, "_1." + ext);// 347x260
		String picPath2 = relativePath.replaceAll("\\." + ext, "_2." + ext);// 188x141
		String picPath3 = relativePath.replaceAll("\\." + ext, "_3." + ext);// 130x98
		String picPath4 = relativePath.replaceAll("\\." + ext, "_4." + ext);// 100x75
		String picPath5 = relativePath.replaceAll("\\." + ext, "_5." + ext);// 50x37

		File file = new File(STATICS_BASE_PATH + relativePath);
		File file1 = new File(STATICS_BASE_PATH + picPath1);
		File file2 = new File(STATICS_BASE_PATH + picPath2);
		File file3 = new File(STATICS_BASE_PATH + picPath3);
		File file4 = new File(STATICS_BASE_PATH + picPath4);
		File file5 = new File(STATICS_BASE_PATH + picPath5);

		deletUpFile(file);
		deletUpFile(file1);
		deletUpFile(file2);
		deletUpFile(file3);
		deletUpFile(file4);
		deletUpFile(file5);
	}

	/**
	 * 上传资料
	 * 
	 * @param inFileBytes
	 * @param ext
	 * @param FileType
	 *            资料类型
	 * @return
	 */
	public String uploadFile(byte[] inFileBytes, String ext, FileType fileType) throws Exception {
		final String filename = getFileName(ext);
		String fileDir = UPLOAD_FILE_PATH;

		if (fileType != null) {
			fileDir = getUploadFileDir(fileType);
		}

		String destFilePathSub = null;
		try {
			destFilePathSub = fileDir + filename;
			String destFilePath = STATICS_BASE_PATH + destFilePathSub;
			File destFile = new File(destFilePath);
			if (!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			saveUpFile(inFileBytes, destFile);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("保存文件失败");
		}
		return destFilePathSub;
	}

	/**
	 * 删除文件
	 * 
	 * @param relativePath
	 * @throws Exception
	 */
	public void delFile(String relativePath) throws Exception {
		File file = new File(STATICS_BASE_PATH + relativePath);
		deletUpFile(file);
	}

	public void saveUpFile(byte[] inBytes, File out) throws Exception {

		try {
			OutputStream bos = new FileOutputStream(out);
			;
			bos.write(inBytes);
			bos.close();
			bos = null;
		} catch (FileNotFoundException ex) {
			throw new Exception(ex);
		} catch (IOException ex) {
			throw new Exception(ex);
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	public void deletUpFile(File delefile) {
		if (delefile.exists()) {
			delefile.delete();
		}
	}

	/**
	 * 上传资料（保留上传文件名，注：该目录下如果存在相同文件名则删除旧文件保存此上传文件）
	 * 
	 * @param inFileBytes
	 * @param filename
	 *            上传文件名
	 * @param ext
	 *            上传文件后缀名
	 * @param FileType
	 *            资料类型
	 * @return
	 */
	public String uploadFileFull(byte[] inFileBytes, String filename, String ext, FileType fileType) throws Exception {
		filename = filename + "." + ext;
		File file = new File(filename);
		if (file.exists()) {
			file.delete();
		}
		String fileDir = UPLOAD_FILE_PATH;

		if (fileType.equals(FileType.NEWS_FILE)) {
			fileDir = getUploadFileDir(FileType.NEWS_FILE);
		}

		String destFilePathSub = null;
		try {
			destFilePathSub = fileDir + filename;
			String destFilePath = STATICS_BASE_PATH + destFilePathSub;
			File destFile = new File(destFilePath);
			if (!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			saveUpFile(inFileBytes, destFile);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("保存文件失败");
		}
		return destFilePathSub;
	}

	private String getProductPicDir() {
		Calendar calendar = Calendar.getInstance();
		return PRODUCT_PIC_PATH + "/" + DateUtil.getDateTime("yyyyMM", calendar.getTime()) + "/"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "/";
	}
	private String getActivityPicDir(String activityId){
		return ACTIVITY_PIC_PATH + "/" +activityId + "/";
	}
	private String getProductPicTempDir() {
		Calendar calendar = Calendar.getInstance();
		return PRODUCT_PIC_TEMP_PATH + "/" + DateUtil.getDateTime("yyyyMM", calendar.getTime()) + "/"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "/";
	}
	
	private String getTempfileDir() {
		Calendar calendar = Calendar.getInstance();
		return TEMP_FILE_PATH + "/" + DateUtil.getDateTime("yyyyMM", calendar.getTime()) + "/"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "/";
	}

	

	private String getUploadFileDir(FileType fileType) {
		String basepath = "";
		if (fileType.equals(FileType.MEMBER_FILE)) {
			basepath = UPLOAD_MEMBER_FILE_PATH;
		} else if (fileType.equals(FileType.PRODUCT_FILE)) {
			basepath = UPLOAD_PRODUCT_FILE_PATH;
		} else if (fileType.equals(FileType.ADVERTISE_FILE)) {
			basepath = UPLOAD_ADVERTISE_FILE_PATH;
		} else if (fileType.equals(FileType.NEWS_FILE)) {
			basepath = UPLOAD_DIGINEWS_FILE_PATH;
		}else if (fileType.equals(FileType.ACTIVITY_FILE)) {
			basepath = UPLOAD_ACTIVITY_FILE_PATH;
		} 
		Calendar calendar = Calendar.getInstance();
		return basepath + "/" + DateUtil.getDateTime("yyyyMM", calendar.getTime()) + "/"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "/";
	}

	private String getFileName(String ext) {
		Date date = Calendar.getInstance().getTime();
		String datestr = DateUtil.getDateTime("yyyyMMddHHmmssSS", date);
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		// System.out.println(datestr+sRand);
		return datestr + sRand + "." + ext;
		// return UUID.randomUUID().toString() + "." + ext;
	}


	
	/**
	 * 获取实际文件存放路径
	 * 
	 * @param tempPath
	 * @return
	 */
	public String getRealPath(String tempPath) {
		String result = "";
		
		String content = tempPath.substring(PRODUCT_PIC_TEMP_PATH.length(), tempPath.length());
		result = PRODUCT_PIC_PATH + content;
		
		return result;
	}
	
	/**
	 * 判断文件路径是否是临时文件
	 * 
	 * @param path
	 * @return
	 */
	public boolean isTempFile(String path) {
		return path.contains(PRODUCT_PIC_TEMP_PATH);
	}
	
	public final static void main(String args[]) {

		Date date = Calendar.getInstance().getTime();
		String datestr = DateUtil.getDateTime("yyyyMMddHHmmssSS", date);
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		System.out.println(datestr + sRand);

		/*
		 * for (int i = 0; i <= 20; i++) { String uuid = UUID.randomUUID().toString().replace("-", "");
		 * System.out.println(uuid);
		 * 
		 * }
		 */
	}

}
