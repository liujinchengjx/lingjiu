package com.liu.qinziyou.common.tree;
/**
 * 这个是菜单树，他的数据用来绑定 index.html的菜单控件
 * @author Administrator
 *
 */
public class MenuTree {
	
	  private String id = "";//本级Id
	  private String pid = ""; //父级ID
	  private String text = ""; //模块名称
	  private boolean expanded = false; //是否展开 
	  private String url = "";//链接地址
	  public MenuTree(){		  	  
	  }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
