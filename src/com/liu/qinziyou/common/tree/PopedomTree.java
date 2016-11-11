package com.liu.qinziyou.common.tree;
/**
 * 这个是权限树类，他的数据用来以树状显示所有权限
 * @author Administrator
 *
 */
public class PopedomTree {
	  private String id = "";//本级Id  positionCode
	  private String pid = ""; //父级ID
	  private String text = ""; //模块名称  positionName
	  private boolean expanded = false; //是否展开
	  private Integer positionStatus;// 状态值
	  private Integer positionLevel;
	  private Integer positionIndex;
	  
	  public PopedomTree(){		  	  
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
		public Integer getPositionStatus() {
			return positionStatus;
		}
		public void setPositionStatus(Integer positionStatus) {
			this.positionStatus = positionStatus;
		}
		public Integer getPositionLevel() {
			return positionLevel;
		}
		public void setPositionLevel(Integer positionLevel) {
			this.positionLevel = positionLevel;
		}
		public Integer getPositionIndex() {
			return positionIndex;
		}
		public void setPositionIndex(Integer positionIndex) {
			this.positionIndex = positionIndex;
		}
}
