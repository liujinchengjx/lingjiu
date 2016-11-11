package com.liu.qinziyou.common.report;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.liu.qinziyou.common.util.DateUtil;
import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Section;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * pdf 工具类
 * 
 * @author Administrator
 * 
 */
public class PdfUtil {
	private  BaseFont bfChinese;
	private  Font fontChinese;
	private  Font fontEnglish;
	public PdfUtil() {
		try {
			// 声明中文字体，使iText支持中文
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			fontChinese= new Font(bfChinese, 10, Font.NORMAL);
			fontEnglish= FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL,Color.BLACK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public  void genPDF(HttpServletResponse response) {
		
		try {} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  Cell getCell(String text,Integer borderWith,Integer colspan,Font ft) throws Exception{
		  Phrase  ph  = new  Phrase(text,  ft); // ChangeChinese(text,nfontsize,isBold);		  		
		  Cell cell = new Cell(ph);
		 // cell.setUseAscender(true); 
		  cell.setVerticalAlignment(Cell.ALIGN_CENTER); 
		  cell.setColspan(colspan);
		  cell.setBorderWidth(borderWith);
		  return cell;
	}
	public  Phrase getPhrase(String text,Font ft) throws Exception{		 
		 Phrase  ph  = new  Phrase(text,  ft); //
		 return ph;
	}
	public  Chunk  getChunk (String text ,Font ft) throws Exception{
		 Chunk   ck  = new  Chunk (text,  ft); //
		 return ck;
	}
	
	public  Table getTable(Integer borderWith,Integer cols) throws Exception{
		  Table t = new Table(cols);
		  t.setBorderWidth(borderWith);
		  return t;
	}
	
	
	
	

}
