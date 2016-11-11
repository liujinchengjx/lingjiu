package com.liu.qinziyou.common;

import java.util.List;

import com.liu.qinziyou.entity.BaseBean;

public class PageHelper { //extends Message
	private int pageSize=10;
	private int total;
	private int start=1;
	private int firstRecordNumber;
	private int lastRecorNumber;
	private int totalPages;
	private List<BaseBean> rows ; 
	private int offSet;
	private int prePage;
	private int nextPage;
	
	public int getStart() {		
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}

	public long getFirstRecordNumber() {
		return firstRecordNumber;
	}
	public void setFirstRecordNumber(int firstRecordNumber) {
		this.firstRecordNumber = firstRecordNumber;
	}
	public int getLastRecorNumber() {
		return lastRecorNumber;
	}
	public void setLastRecorNumber(int lastRecorNumber) {
		this.lastRecorNumber = lastRecorNumber;
	}
	public int getPageSize() {
		if(pageSize==0)
		{
			pageSize=10;
		}
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPages() {
		if(pageSize==0)
		{
			pageSize=10;
		}
		return (total%pageSize)>0?total/pageSize+1:total/pageSize;
	
	}
	public void setTotalPages(int totalPages) {
		
		this.totalPages = totalPages;
	}	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getOffSet() {
		 if(start>getTotalPages()){
			 start=getTotalPages();	
		 }
		 if(start<1){
			 start=1;
		}
		offSet=(start-1)*getPageSize();
		return offSet;
	}
	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}
	public List<BaseBean> getRows() {
		return rows;
	}
	public void setRows(List<BaseBean> rows) {
		this.rows = rows;
	}
	public int getPrePage() {
		return this.getStart()==1?1:this.getStart()-1;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getNextPage() {
		return this.getTotalPages()==this.getStart()?this.getTotalPages():this.getStart()+1;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	public static void main(String[] args)
	{
		PageHelper pageHelper = new PageHelper();
		pageHelper.setTotal(95);
		pageHelper.setPageSize(10);
		pageHelper.setStart(11);
		System.out.println("pageHelper.getNextPage()="+pageHelper.getNextPage());
		System.out.println("pageHelper.getPrePage()="+pageHelper.getPrePage());
		System.out.println("pageHelper.getTotalPages()="+pageHelper.getTotalPages());
	
	}
	
	
	
	
}
