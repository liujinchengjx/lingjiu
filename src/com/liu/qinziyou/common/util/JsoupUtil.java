package com.liu.qinziyou.common.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.liu.qinziyou.entity.business.ProductTaxCode;


/**
 * <p>Title: </p>
 * <p>Description:通用工具 类似于html端的jquery,可以解析html文档 在线抓取网页数据</p>
 * <p>创建日期:2011-10-25</p>
 * @author li
 * @email 
 * @version 1.0 
 * <p>gscm</p>
 * <p></p>
 */
public class JsoupUtil {
   
//    Element table = doc.getElementsByTag("table").get(6).getElementsByTag("table").get(0).getAllElements().get(1)
//    .getElementsByTag("table").get(0);
	
	public static void gethtml(String url){
	    long start = System.currentTimeMillis();  
	    Document doc=null;  
	    try{  
	        doc = Jsoup.connect(url).timeout(2000).get();
	    }  
	    catch(Exception e){  
	        e.printStackTrace();  
	    }  
	    finally{  
	        System.out.println("Time is:"+(System.currentTimeMillis()-start) + "ms");  
	    }
	    Element tb= doc.select("[width=800][border=0][cellpadding=5][cellspacing=1][bgcolor=#EAEAEA]").get(0);
	    Elements trs=tb.getElementsByTag("tr");
	    System.out.println("table is:" +trs.text());  
	}
	
	public static ProductTaxCode analyzeProductTaxCodeHtml(String html){
		ProductTaxCode ptc=null;
		Double lowRate=0.0D,commRate=0.0D,expReTaxRate=0.0D,addRate=0.0D,consumeRate=0.0D;
		Document doc=null;
	    try{  
	        doc = Jsoup.parse(html);
	        Element tb= doc.select("[cellspacing=0][rules=all][border=0][id=GridView1]").get(0);
		    Elements trs=tb.getElementsByTag("tr");
		    for(int i=1;i<trs.size();i++){
		    	if (ptc==null){
		    		ptc=new ProductTaxCode();
		    	}
		    	Elements tds=trs.get(i).getElementsByTag("td");
		    	if (!("".equals(tds.get(0).text()))){
		    		ptc.setCodeTs(tds.get(0).text());
    			}
		    	if (!("".equals(tds.get(1).text()))){
		    		ptc.setProductName(tds.get(1).text());
    			}
    			if (!("".equals(tds.get(2).text()))){
    				lowRate=Double.parseDouble(tds.get(2).text());
    				ptc.setLowRate(lowRate);
    			}
    			if (!("".equals(tds.get(3).text()))){
    				commRate=Double.parseDouble(tds.get(3).text());
    				ptc.setCommRate(commRate);
    			}
    			if (!("".equals(tds.get(4).text()))){
    				expReTaxRate=Double.parseDouble(tds.get(4).text());
    				ptc.setExpReTaxRate(expReTaxRate);
    			}
    			if (!("".equals(tds.get(5).text()))){
    				addRate=Double.parseDouble(tds.get(5).text());
    				ptc.setAddRate(addRate);
    			}
				if (!("".equals(tds.get(6).text()))){
					consumeRate=Double.parseDouble(tds.get(6).text());
					ptc.setConsumeRate(consumeRate);
				}
				if (!("".equals(tds.get(7).text()))){
					String unit=tds.get(7).text();
					if (unit.contains("(") && unit.contains(")") ){
						int bIndex=unit.indexOf("(");
						int eIndex=unit.indexOf(")");
						ptc.setUnit2(unit.substring(bIndex+1,eIndex));
						unit=unit.substring(0,bIndex);
					}
					ptc.setUnit1(unit);
				}
				if (!("".equals(tds.get(8).text()))){
					ptc.setControlMa(tds.get(8).text());
				}
			}
	    }  
	    catch(Exception e){  
	        e.printStackTrace();  
	    }finally{  
	    } 
		return ptc;
	}
}
