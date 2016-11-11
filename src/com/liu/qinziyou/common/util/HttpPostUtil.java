package com.liu.qinziyou.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * <p>Title: </p>
 * <p>Description:通用工具</p>
 * <p>创建日期:2011-10-25</p>
 * @author li
 * @email 
 * @version 1.0 
 * <p>gscm</p>
 * <p></p>
 */
public class HttpPostUtil {
	private final static Log logger = LogFactory.getLog(HttpPostUtil.class);
	
	public static String getURLSource(URL url) throws Exception    {
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream =  conn.getInputStream();  //通过输入流获取html二进制数据
        byte[] data = readInputStream(inStream);        //把二进制数据转化为byte字节数据
        String htmlSource = new String(data);
        conn=null;
        return htmlSource;
	}

    /**
     * 把二进制流转化为byte字节数组
     * @param instream
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream instream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[]  buffer = new byte[1204];
        int len = 0;
        while ((len = instream.read(buffer)) != -1){
            outStream.write(buffer,0,len);
        }
        instream.close();
        return outStream.toByteArray();         
    }
   /**
    * 获取网页的html内容
    * url
    * @return
    */
	public static String getRateHtmlContent(String url,String currDate,String pjname,int pageNum) {
		if ("".equals(pjname)) return null;
		if (pjname==null) return null;
		String tempUrl=url+"?erectDate="+currDate+"&nothing="+currDate+"&pjname="+pjname+"&page="+pageNum;
		String urlsource="";
		try{
			URL urlObjet = new URL(tempUrl); 
			urlsource = getURLSource(urlObjet);
			Document doc=Jsoup.parse(urlsource);
			Element tb= doc.select("[cellpadding=0][cellspacing=0][width=100%][align=left]").get(0);
			urlsource=tb.toString();
		}catch(Exception ex){
			ex.printStackTrace();		
		}finally{
        }
	    return urlsource;
//		HttpClient client =new DefaultHttpClient();
//		String remoteDates="";
//		HttpPost post=new HttpPost(url);
//		List<NameValuePair> parms=new ArrayList<NameValuePair>();
//		parms.add(new BasicNameValuePair("erectDate",currDate)); //
//		parms.add(new BasicNameValuePair("nothing",currDate)); //
//		parms.add(new BasicNameValuePair("pjname",pjname));
//		parms.add(new BasicNameValuePair("page",String.valueOf(pageNum))); //从第一页开始找
//		try{
//			post.setEntity(new UrlEncodedFormEntity(parms, HTTP.UTF_8));
//			HttpResponse response = client.execute(post); 
//			if(response.getStatusLine().getStatusCode() ==200){
//			   remoteDates = EntityUtils.toString(response.getEntity());
//		    } 
//		}catch(Exception ex){
//			ex.printStackTrace();			  
//		}finally{
//			client.getConnectionManager().shutdown(); 
//		}
//		return remoteDates;
	}
	 /**
    * 在线获商品税率的网页html内容
    * url
    * @return
	 * @throws IOException 
    */
	public static String getProductTaxCodeHtmlContent(String url,String codeTs,int count){
		if (codeTs==null) return null;
		String remoteDates="";
		url = url+codeTs;
		try{
			HttpPost post=new HttpPost(url);
			HttpClient client =new DefaultHttpClient();
			post.addHeader("user-agent", "test-user-agent");
			post.addHeader("x-forwarded-for", "127.0.0."+count);
			post.addHeader("x-forwarded-server", "www.test"+count+".com");
			post.addHeader("x-forwarded-host", "www.test"+count+".com");
			post.addHeader("referer", "www.test"+count+".com/test.html");
			HttpResponse response = client.execute(post); 
			if(response.getStatusLine().getStatusCode() ==200){ 
				remoteDates = EntityUtils.toString(response.getEntity());
				//System.out.println(remoteDates);
			} else{
				logger.info("取税号失败："+url);
			}
			
		 }catch (Exception localException) {
			 logger.error("取税号发生错误："+url);
		 }
		return remoteDates.toString();
	}
	
	public static void main(String[] args) throws Exception{ 
		String url ="http://query.customs.gov.cn/hyw2007dataquery/Spcx/SpcxSearchListDetail.aspx?ID=223233";
		//url="http://www.gscm.com/service/jsonrest/systemmanager/FrameworkButtons/0/searchButtons?searchValue=&start=0&limit=20&_time_stamp_=1389246794157";
		for(int i=10000;i<=10015;i++){
			HttpPost post=new HttpPost(url);
			HttpClient client =new DefaultHttpClient();
			post.addHeader("user-agent", "michael-user-agent");
			post.addHeader("x-forwarded-for", "127.0.0."+i);
			//post.addHeader("x-forwarded-server", "www.gscm"+i+".com");
			//post.addHeader("x-forwarded-host", "www.gscm"+i+".com");
			//post.addHeader("referer", "test"+i+".html");
			try{
				HttpResponse response = client.execute(post); 
				if(response.getStatusLine().getStatusCode() ==200){ 
					  String remoteDates = EntityUtils.toString(response.getEntity());
					  System.out.println(remoteDates);
				 } else{
					 System.out.println(response.getStatusLine().getStatusCode());
				 }
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}
}
